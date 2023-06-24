package Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Airplane {
    //primary key
    private int id;
    //비행기 이름
    private String airplaneName;
    //출발시간
    private Date departureTime;
    //출발지
    private String startDestination;
    //도착지
    private String endDestination;
    // 좌석 리스트
    public List<String> seatList;

    public Airplane(int id, String airplaneName, Date departureTime, String startDestination, String endDestination, int totalRows, int seatsPerRow) {
        this.id = id;
        this.airplaneName = airplaneName;
        this.departureTime = departureTime;
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.seatList = generateSeatList(totalRows, seatsPerRow);
    }

    // 행과 열로 구성된 좌석 리스트 생성하는 메서드
    public List<String> generateSeatList(int totalRows, int seatsPerRow) {

        // 좌석을 저장할 빈 리스트 생성
        List<String> seatList = new ArrayList<>();

        // 좌석의 행 레이블을 나타내는 변수를 A로 초기화
        char rowLabel = 'A';

        // 주어진 행의 수만큼 반복, 행은 1부터 시작하며 totalRows 까지 증가
        for (int row = 1; row <= totalRows; row++) {

            // 한 행당 좌석의 수만큼 반복 좌석은 1부터 시작하며 seatsPerRows 까지 증가
            for (int seat = 1; seat <= seatsPerRow; seat++) {

                // rowLabel과 seat 값을 사용하여 좌석 이름 생성
                // String.format() 메서드를 사용하여 형식화된 문자열 생성, %c: 문자 | %02d: 2자리로 구성된 숫자
                String seatName = String.format("%c%02d", rowLabel, seat);

                // 생성된 좌석 이름을 seatList에 추가
                seatList.add(seatName);
            }

            rowLabel++;
        }
        return seatList;
    }

    public int getId() {
        return id;
    }

    public String getAirplaneName() {
        return airplaneName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public List<String> getSeatList() {
        return seatList;
    }


    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", airplaneName='" + airplaneName + '\'' +
                ", departureTime=" + departureTime +
                ", startDestination='" + startDestination + '\'' +
                ", endDestination='" + endDestination + '\'' +
                '}';
    }
}
