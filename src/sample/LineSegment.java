package sample;

/**
 * Created by jared_000 on 7/17/2016.
 */
public class LineSegment {
    double slope;
    double intercept;
    DataPoint begining;
    DataPoint end;
    double xChange;
    double yChange;
    boolean specialLine = false;

    public LineSegment(DataPoint beginingInput, DataPoint endInput) {
        begining = beginingInput;
        end = endInput;

        calcSlope();
        calcIntercept();
    }

    public LineSegment(double x, double y, double xOne, double yOne) {
        begining = new DataPoint(x, y);//x <= xOne ? new DataPoint(x, y): new DataPoint(xOne, yOne);
        end = new DataPoint(xOne, yOne);//x >= xOne ? new DataPoint(x, y): new DataPoint(xOne, yOne);

        calcSlope();
        calcIntercept();
    }

    public LineSegment() {
    }

    public void calcSlope() {
        if ((begining.x - end.x) != 0 & (begining.y - end.y) != 0) {
            slope = (begining.y - end.y) / (begining.x - end.x);
        } else {
            specialLine = true;
        }
    }

    public void calcIntercept() {
        intercept = begining.y - (slope * begining.x);
    }

    public boolean segmentContains(double x, double y) {
        if (yAt(x) == y) {
            return true;
        }

        return false;
    }

    public boolean segmentContainsRounded(double x, double y) {
        if (Math.round(yAt(Math.round(x))) == Math.round(y)) {
            return true;
        }

        return false;
    }

    public double yAt(double xPosition) {
        if (specialLine == true) {
            return begining.y;
        }
        return (xPosition * slope) + intercept;
    }
}



