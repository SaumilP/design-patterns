package design.patterns.command;

/**
 * Created by PATEL1 on 12/28/14.
 */
public enum Visibility {
    VISIBLE, INVISIBLE;

    @Override public String toString(){
        String str = "";
        switch (this){
            case INVISIBLE: str = "invisible"; break;
            case VISIBLE: str = "visible"; break;
            default: break;
        }
        return str;
    }
}
