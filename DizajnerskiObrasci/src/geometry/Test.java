package geometry;

public class Test {

	public static void main(String[] args) {

		// Vezbe 1 i 2
		System.out.println("Test klasa");

		
		System.out.println("Kreiranje objekta");
		Point p = new Point();
		// System.out.println(p.x + " "+ p.y+ " "+p.selected);

		System.out.println(p.getX());
		p.setX(10);
		System.out.println(p.getX());

		p.setY(20);
		System.out.println("Udaljenost je: " + p.distance(15, 25));

		// Vezbe 3
		// p -> x=10, y=20
		p.setSelected(true);
		Point p1 = new Point();
		p1.setX(20);
		p1.setY(25);

		Line l1 = new Line();
		System.out.println(l1.getStartPoint() + " " + l1.getEndPoint());
		System.out.println(l1.isSelected());
		
		Rectangle r1=new Rectangle();
		Circle c1=new Circle();

		// 1. Inicijalizovati x koordinatu tacke p
		// na vrednost y koordinate tacke p1
		System.out.println(p.getX());
		p.setX(p1.getY());
		System.out.println(p.getX());

		// 2. Postaviti za pocetnu tacku linije l1 tacku p, a
		// za krajnju tacku linije l1 tacku p1
		l1.setStartPoint(p);
		l1.setEndPoint(p1);
		System.out.println(l1.getStartPoint().getX() + " " +l1.getStartPoint().getY());
		System.out.println(l1.getEndPoint().getX() + " " +l1.getEndPoint().getY());

		// 3. Postaviti y koordinatu krajnje tacke l1 na 23
		System.out.println(l1.getEndPoint().getY());
		l1.getEndPoint().setY(23);
		System.out.println(l1.getEndPoint().getY());

		// 4. Inicijalizovati x koordinatu pocetne tacke linije l1
		// na vrednost y koordinate krajnje tacke linije l1
		System.out.println(l1.getStartPoint().getX());
		l1.getStartPoint().setX(l1.getEndPoint().getY());
		System.out.println(l1.getStartPoint().getX());

		// 5. Postaviti x koordinatu krajnje tacke l1 na vrednost
		// duzine linije l1 umanjene za vrednost zbira x i y
		// koordinate pocetne tacke linije l1
		System.out.println(l1.getEndPoint().getX());
		l1.getEndPoint().setX((int)(l1.length()-(l1.getStartPoint().getX()+l1.getStartPoint().getY())));
		System.out.println(l1.getEndPoint().getX());

		// 6. Postaviti x koordinatu tacke gore levo pravougaonika
		// r1 na vrednost 10 i y koordinatu na vrednost 15
		System.out.println(r1.getUpperLeftPoint());
		//r1.getUpperLeftPoint().setX(10);
		r1.setUpperLeftPoint(p);
		r1.getUpperLeftPoint().setX(10);
		r1.getUpperLeftPoint().setY(15);

		// 7. Postaviti centar kruga c1 na koordinate tacke
		// gore levo od r1
		c1.setCenter(r1.getUpperLeftPoint());

		// 8. Postaviti x koordinatu centra kruga c1 na vrednost razlike
		// povrsine pravougaonika r1 i y koordinate pocetne tacke linije l1
		r1.setHeight(10);
		r1.setWidth(20);
		c1.getCenter().setX(r1.area()-l1.getStartPoint().getY());
		System.out.println(c1.getCenter().getX());
	}

}
