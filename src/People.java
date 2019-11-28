public class People {
    private String name;
    private String tel;

    People() {
        name = "";
        tel = "";
    }

    People(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public void print(){
        System.out.println(name+" "+tel);
    }
}
