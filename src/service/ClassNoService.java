package service;

@Service
public class ClassNoService{
    public ClassNoService() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
