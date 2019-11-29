public class People {
    private String name;
    private String tel;
    private int award;

    People() {
        name = "";
        tel = "";
        award = 0;
    }

    People(String name, String tel) {
        this.name = name;
        this.tel = tel;
        this.award = 0;
    }

    String getName() {
        return name;
    }

    String getTel() {
        return tel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    void setAward(int award) {
        this.award = award;
    }

    int getAward() {
        return award;
    }

    void print() {
        System.out.println(name + " " + tel + " " + award);
    }
}
