package design.patterns.command;

/**
 * Created by PATEL1 on 12/28/14.
 */
public enum Size {
    SMALL, NORMAL, LARGE;

    @Override public String toString(){
        String str = "";
        switch (this){
            case LARGE: str = "large"; break;
            case SMALL: str = "small"; break;
            case NORMAL: str = "normal"; break;
            default: break;
        }
        return str;
    }
}
