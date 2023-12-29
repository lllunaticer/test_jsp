package JsonTest;

/**
 * @author yanglan <yanglan05@kuaishou.com>
 * Created on 2021-05-13
 */
public class JsonDiffScope {

    private boolean same = true;

    private ViewDiffResult viewDiffResult = new ViewDiffResult();

    public JsonDiffScope(boolean same) {
        this.same = same;
    }

    public boolean isSame() {
        return same;
    }

    public void setSame(boolean same) {
        this.same = same;
    }

    public ViewDiffResult getViewDiffResult() {
        return viewDiffResult;
    }

    public void setViewDiffResult(ViewDiffResult viewDiffResult) {
        this.viewDiffResult = viewDiffResult;
    }
}
