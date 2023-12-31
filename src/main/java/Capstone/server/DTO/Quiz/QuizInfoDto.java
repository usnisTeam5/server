package Capstone.server.DTO.Quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuizInfoDto {
    int quizKey;
    String quizName;
    int quizNum;  //문제 총 갯수
    int curNum; //현재 진행중인 문제 갯수
}
