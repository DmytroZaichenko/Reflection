package service;

@Service
public class ClassService {

    public ClassService() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
