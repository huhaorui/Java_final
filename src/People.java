class People {
    private String name;
    private String tel;
    private int award;

    People(String name, String tel) {
        this.name = name;
        this.tel = tel;
        this.award = 0;
    }
    People(String name,String tel,int award){
        this.name=name;
        this.tel=tel;
        this.award=award;
    }
    String getName() {
        return name;
    }

    String getTel() {
        return tel;
    }

    void setAward(int award) {
        this.award = award;
    }

    int getAward() {
        return award;
    }

}
