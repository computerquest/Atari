package sample;

/**
 * Created by jared_000 on 7/17/2016.
 */
public class LineSegment {
    double slope;
    double intercept;
    DataPoint begining;
    DataPoint end;
    boolean specialLine = false; //only is true if the line is horizontal of vertical (can't handle verticle)

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

    //calculates slope of the line
    public void calcSlope() {
        if ((begining.x - end.x) != 0 & (begining.y - end.y) != 0) {
            slope = (begining.y - end.y) / (begining.x - end.x);
        } else {
            specialLine = true;
        }
    }

    //calculates the intercept of the line
    public void calcIntercept() {
        intercept = begining.y - (slope * begining.x);
    }

    //sees if a point is in the line
    public boolean segmentContains(double x, double y) {
        if (yAt(x) == y) {
            return true;
        }

        return false;
    }

    //gets the y at x
    public double yAt(double xPosition) {
        if (specialLine == true) {
            return begining.y;
        }
        return (xPosition * slope) + intercept;
    }
}



