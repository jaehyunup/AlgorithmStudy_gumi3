import java.util.Arrays;

public class phone_visibility {
    public static void main(String[] args) {
       phone_visibility d=new phone_visibility();
        System.out.println(phone_visibility.solution("010123456782-"));
    }

    public static int solution(String phone_number) {
        String[] number_data=phone_number.split("-",-1);
        System.out.println(Arrays.toString(number_data));
        switch(number_data.length){
            case 1: // 일반 숫자
                if(number_data[0].length()==11){
                    if(number_data[0].toString().substring(0,3).equals("010")){
                        return 2;
                    }
                }
                break;
            case 3: // - 가 포함된 숫자
                if(number_data[0].equals("010")){
                    if(number_data[1].length()==4 && number_data[2].length()==4){
                        return 1;
                    }
                }
                break;
            case 4: // 국가 번호
                if(number_data[0].length()==3 && number_data[0].indexOf("+")==0 && number_data[0].contains("82")){
                    // +82 인가 검증
                    if(number_data[1].equals("10")){
                        if(number_data[2].length()==4 && number_data[3].length()==4){
                            return 3;
                        }
                    }
                }
                break;
        }
       return -1;
    }
}
