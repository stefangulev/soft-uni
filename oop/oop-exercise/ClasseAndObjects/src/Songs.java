import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Songs {
    static class SongsClass {
        private String typeList;
        private String songName;
        private String length;

        public String getTypeList () {

            return typeList;
        }

        public void setTypeList(String typeList) {
            this.typeList = typeList;
        }

        public String getSongName () {
            return songName;
        }
        public void setSongName (String songName) {
            this.songName = songName;
        }
        public String getLength ()
        {
            return length;
        }
        public void setLength(String length)
        {
            this.length = length;
        }

    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        List<SongsClass> list = new ArrayList<>();

        for (int i = 0; i < n ; i++) {
            String[] read = scan.nextLine().split("_");
            SongsClass songs = new SongsClass();
            songs.setTypeList(read[0]);
            songs.setSongName(read[1]);
            songs.setLength(read[2]);

            list.add(songs);

        }

        String typeListInput = scan.nextLine();

        if (typeListInput.equals("all")) {
            for (SongsClass asd : list) {
                System.out.println(asd.getSongName());
            }
        } else {
            for (SongsClass ads : list) {
                if (ads.getTypeList().equals(typeListInput)) {
                    System.out.println(ads.getSongName());
                }
            }
        }






    }

}
