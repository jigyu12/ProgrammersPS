package 오픈채팅방;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {

    private static final String ENTER = "Enter";
    private static final String CHANGE = "Change";
    private static final String LEAVE = "Leave";

    private static final String ENTER_MSG = "님이 들어왔습니다.";
    private static final String LEAVE_MSG = "님이 나갔습니다.";

    public static String[] solution(String[] record) {
        HashMap<String,ArrayList<Integer>> uidRecordMap = new HashMap<>();
        HashMap<String,String> uidNickNameMap = new HashMap<>();
        ArrayList<String> makeAnswerList = new ArrayList<>();

        for(int i = 0; i < record.length; i++) {
            String[] parseRecord = record[i].split(" ");
            ArrayList<Integer> uidRecordIdxList	= null;

            switch(parseRecord[0]) {

                case ENTER:
                    if(!uidRecordMap.containsKey(parseRecord[1])) {
                        uidRecordIdxList = new ArrayList<>();
                    }
                    else {
                        uidRecordIdxList = uidRecordMap.get(parseRecord[1]);
                    }

                    if(uidNickNameMap.containsKey(parseRecord[1]) &&
                            !uidNickNameMap.get(parseRecord[1]).equals(parseRecord[2])) {
                        for(int j = 0; j < uidRecordIdxList.size(); j++) {
                            StringBuilder makeMsgUpdate = new StringBuilder();
                            makeMsgUpdate.append(parseRecord[2]);
                            int recordIdx = uidRecordIdxList.get(j);
                            if(makeAnswerList.get(recordIdx)
                                    .substring(makeAnswerList.get(recordIdx).length() - 9
                                            , makeAnswerList.get(recordIdx).length())
                                    .equals(LEAVE_MSG)) {
                                makeMsgUpdate.append(LEAVE_MSG);
                            }
                            else {
                                makeMsgUpdate.append(ENTER_MSG);
                            }
                            makeAnswerList.add(recordIdx, makeMsgUpdate.toString());
                            makeAnswerList.remove(recordIdx+1);
                        }
                    }

                    uidNickNameMap.put(parseRecord[1], parseRecord[2]);
                    uidRecordIdxList.add(makeAnswerList.size());
                    uidRecordMap.put(parseRecord[1], uidRecordIdxList);

                    StringBuilder makeMsg = new StringBuilder();
                    makeMsg.append(parseRecord[2]);
                    makeMsg.append(ENTER_MSG);
                    makeAnswerList.add(makeMsg.toString());
                    break;

                case LEAVE:
                    if(!uidRecordMap.containsKey(parseRecord[1])) {
                        uidRecordIdxList = new ArrayList<>();
                        uidRecordIdxList.add(makeAnswerList.size());
                        uidRecordMap.put(parseRecord[1], uidRecordIdxList);
                    }
                    else {
                        uidRecordIdxList = uidRecordMap.get(parseRecord[1]);
                        uidRecordIdxList.add(makeAnswerList.size());
                        uidRecordMap.put(parseRecord[1], uidRecordIdxList);
                    }

                    StringBuilder makeMsgLeave = new StringBuilder();
                    makeMsgLeave.append(uidNickNameMap.get(parseRecord[1]));
                    makeMsgLeave.append(LEAVE_MSG);
                    makeAnswerList.add(makeMsgLeave.toString());
                    break;

                case CHANGE:
                    uidRecordIdxList = uidRecordMap.get(parseRecord[1]);
                    for(int j = 0; j < uidRecordIdxList.size(); j++) {
                        StringBuilder makeMsgChange = new StringBuilder();
                        makeMsgChange.append(parseRecord[2]);

                        int recordIdx = uidRecordIdxList.get(j);

                        if(makeAnswerList.get(recordIdx)
                                .substring(makeAnswerList.get(recordIdx).length() - 9
                                        , makeAnswerList.get(recordIdx).length() - 0)
                                .equals(LEAVE_MSG)) {
                            makeMsgChange.append(LEAVE_MSG);
                        }
                        else {
                            makeMsgChange.append(ENTER_MSG);
                        }
                        makeAnswerList.add(recordIdx, makeMsgChange.toString());
                        makeAnswerList.remove(recordIdx+1);
                    }

                    uidNickNameMap.put(parseRecord[1], parseRecord[2]);
                    break;
            }
        }

        String[] answer = new String[makeAnswerList.size()];

        for(int j = 0; j < makeAnswerList.size(); j++) {
            answer[j] = makeAnswerList.get(j);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234",
                "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] answer = solution(record);

        for(int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

}
