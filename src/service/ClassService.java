package service;

@Service
public class ClassService {

    public ClassService() {
        System.out.println(toString());
    }

    @InitService
    public void oneMethod(){
        System.out.println(this.toString() + "run the first method ");
    }

    @InitService
    public void secondMethod(){
        System.out.println(this.toString() + "run the second method ");
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
