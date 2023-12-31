package Capstone.server.Service;

import Capstone.server.DTO.Qa.*;
import Capstone.server.Repository.QaRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class QaService {
    QaRepository qaRepository;
    ProfileService profileService;

    QaService(QaRepository qaRepository,
              ProfileService profileService) {
        this.qaRepository = qaRepository;
        this.profileService = profileService;
    }

    public int enrollQa(QaDto qaDto) {
        return qaRepository.enrollQa(qaDto);
    }

    public boolean isReview(int qaKey) {
        return qaRepository.isReview(qaKey);
    }

    public String deleteQa(int qaKey) {
        return qaRepository.deleteQa(qaKey);
    }

    public List<QaBriefDto> getQaList(String nickname) {
        return qaRepository.getQaList(nickname, profileService.getUserCourse(nickname));
    }

    public List<QaMsgDto> getQuestion(int qaKey, String nickname) {
        return qaRepository.getQuestion(qaKey, nickname);
    }

    public void qaGiveUp(int qaKey, String nickname) {
        qaRepository.qaGiveUp(qaKey, nickname);
    }

    public List<QaAlarmDto> getQaAlarm(String nickname) {
        return qaRepository.getQaAlarm(nickname);
    }

    public void qaSolve(int qaKey) {
        qaRepository.qaSolve(qaKey);
    }

    public void sendQaMsg(int qaKey, QaSendMsgDto msg) {
        qaRepository.sendQaMsg(qaKey, msg);
    }

    public List<QaMsgDto> getQaMsgs(int qaKey, String nickname) {
        return qaRepository.getQaMsgs(qaKey, nickname);
    }

    public List<QaAskListDto> getQaAskList(String nickname) {
        return qaRepository.getQaAskList(nickname);
    }

    public List<QaListDto> getQaAnswerList(String nickname) {
        return qaRepository.getQaAnswerList(nickname);
    }

    public void qaFinish(int qaKey, int review) {
        qaRepository.qaFinish(qaKey, review);
    }

    public List<QaMsgDto> getQa(int qaKey) {
        return qaRepository.getQa(qaKey);
    }

    public long getTime(int qaKey) { return qaRepository.getTime(qaKey); }

    public String getQaStatus(int qaKey) {
        return qaRepository.getQaStatus(qaKey);
    }

    public boolean getIsWatching(int qaKey) {
        return qaRepository.isWatching(qaKey);
    }
}
