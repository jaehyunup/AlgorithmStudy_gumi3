import java.util.*;

public class programmers_여행경로 {
    static int ticketLen=0;
    public static void main(String[] args) {
        programmers_여행경로 demo = new programmers_여행경로();
        System.out.println(Arrays.toString(demo.solution(
                new String[][]
                        {{"ICN", "A"}, {"ICN","A"}, {"A","ICN"}})));
    }

    public String[] solution(String[][] tickets) {
        String[] answer = {};
        ticketLen=tickets.length;
        ArrayList<String> path = new ArrayList<String>();
        Map<String,Boolean> visited=new HashMap<String,Boolean>();
        Map<String, ArrayList<String>> ticketMap = new HashMap<String, ArrayList<String>>();
        for (String[] ticket : tickets) {
            ArrayList<String> arriveList;
            if (!ticketMap.containsKey(ticket[0])) {
                arriveList = new ArrayList<>();
                ticketMap.put(ticket[0], arriveList);
            }
            arriveList = ticketMap.get(ticket[0]);
            arriveList.add(ticket[1]);
            ticketMap.put(ticket[0], arriveList);
            visited.put(ticket[1],false);

        }
        for (Map.Entry<String, ArrayList<String>> entry : ticketMap.entrySet()) {
            entry.getValue().sort(String::compareTo);
        }
        path.add("ICN");
        go(ticketMap, path, "ICN");
        return path.toArray(new String[path.size()]);
    }

    public boolean go(Map<String, ArrayList<String>> ticketMap, ArrayList<String> path, String lastArrive) {

        if(path.size()>ticketLen){
            return true;
        }

        if(ticketMap.get(lastArrive)==null || ticketMap.get(lastArrive).size()==0) {
            return false;
        }
        for (int i = 0; i < ticketMap.get(lastArrive).size(); i++) {
            String nextArrive = ticketMap.get(lastArrive).get(i); // i번째 놈을 꺼냅니다.
            ticketMap.get(lastArrive).remove(nextArrive);// i번째 놈을 삭제하고
            path.add(nextArrive); // 적어놓습니다
            if(go(ticketMap,path, nextArrive)) { // 다음출발지로 이동을 때립니다
                return true;
            }else{
                ticketMap.get(lastArrive).add(nextArrive); // 이동이 실패했습니다 백트리킹이죠?
                path.remove(path.size()-1); // 도착지에서도 삭제를 다시 해주죠~
                ticketMap.get(lastArrive).sort(String::compareTo); // 그다음 솔트를 갈겨줍니다~
            }


        }
        return false;
    }
}
