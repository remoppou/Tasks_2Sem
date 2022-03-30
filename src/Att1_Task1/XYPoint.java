package Att1_Task1;


/**
 * Точка на плоскости
 */
public class XYPoint {

    private double x = -1;  // поля можно инициализировать при объявлении
    private double y;

    /**
     * Конструктор по умолчанию (без параметров)
     */
    public XYPoint() {
        y = -1;  // поля можно инициализировать в конструкторе
    }

    /**
     * Конструктор с параметрами
     * @param x
     * @param y
     */
    public XYPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Конструктор копирования
     * @param p
     */
    public XYPoint(XYPoint p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Перемещение точки
     * @param dx
     * @param dy
     */
    public void move(double dx, double dy) {
        this.setX(this.getX() + dx);
        setY(getY() + dy);
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        System.out.printf("Set %.03f to X%n", x);
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        // демонстрации "защиты" поля класса от недопустимых значений (по модулю > 100)
        if (y > 100) {
            y = 100;
        }
        if (y < -100) {
            y = -100;
        }

        this.y = y;
    }

    /**
     * Расстояние до точки от центра координат
    */
    public double getR() {
        return Math.sqrt(x * x + y * y);
    }


    public static XYPoint add(XYPoint a, XYPoint b) {
        XYPoint c = new XYPoint(a.getX() + b.getX(), a.getY() + b.getY());
        return c;
    }

    public XYPoint add(XYPoint b) {
        XYPoint c = new XYPoint(this.getX() + b.getX(), this.getY() + b.getY());
        return c;
    }
}
