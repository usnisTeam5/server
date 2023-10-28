package Capstone.server.Repository;

import Capstone.server.DTO.Enroll.UserDto;
import Capstone.server.DTO.Login.LoginDataDto;
import Capstone.server.Service.UtilService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnrollRepository {
    private JdbcTemplate jdbcTemplate;
    private UtilService utilService;

    public EnrollRepository(JdbcTemplate jdbcTemplate,
                            UtilService utilService) {
        this.jdbcTemplate = jdbcTemplate;
        this.utilService = utilService;
    }

    public Boolean checkDuplicateEmail(String email) {
        String sql = "select email from user where user = ?;";
        List<String> emails = jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new String(rs.getString("email"));
        }, email);

        if(emails.isEmpty())
            return false;
        else
            return true;
    }

    public Boolean checkDuplicateNickname(String nickname) {
        String sql = "select nickname from user where nickname = ?;";
        List<String> nicknames = jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new String(rs.getString("nickname"));
        }, nickname);

        if(nicknames.isEmpty())
            return false;
        else
            return true;
    }

    public List<String> findCourse(String keyword) {
        String findCourseSql = "select course_name from course where course_" +
                "name like '%?%' or professor like '%?%' or major_and_area like '%?%'";
        List<String> courses = jdbcTemplate.query(findCourseSql, (rs, rowNum) -> {
            return new String(rs.getString("course_name"));
        }, keyword);

        return courses;
    }

    public List<String> findDepartment(String keyword) {
        String findDeptSql = "select dept_name from department where dept_name like '%?%';";
        List<String> departments = jdbcTemplate.query(findDeptSql, (rs, rowNum) -> {
            return new String(rs.getString("dept_name"));
        }, keyword);

        return departments;
    }

    public void enrollUser(UserDto userDto) {
        String enrollUserSql = "insert into user (nickname, email, password, introduction," +
                "profile_image_path, point, study_cnt) values (?, ?, ?, ?, ?, ?, ?);";
        String insertMajorSql = "insert into major_in (nickname, dept_name1, dept_name2) " +
                "values (?, ?, ?, ?);";
        String insertTakeSql = "insert into take (nickname, course_name, is_now, is_past, is_pick) " +
                "values (?, ?, ?, ?, ?);";

        String passwordHash = utilService.makeHashcode(userDto.getPassword());
        jdbcTemplate.update(enrollUserSql, userDto.getNickname(), userDto.getEmail(), passwordHash,
                "", "", 0, 0);
        jdbcTemplate.update(insertMajorSql, userDto.getNickname(), userDto.getDept_name1(), userDto.getDept_name2());
        for (String course_name : userDto.getCourse_name())
            jdbcTemplate.update(insertTakeSql, userDto.getNickname(), course_name, true, false, false);
    }

    public void changePassword(LoginDataDto loginDataDto) {
        String passwordHash = utilService.makeHashcode(loginDataDto.getPassword());
        String changePasswordSql = "update user set password = ? where email = ?;";
        jdbcTemplate.update(changePasswordSql, passwordHash, loginDataDto.getEmail());
    }
}
