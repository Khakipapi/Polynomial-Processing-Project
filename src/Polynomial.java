/*
*Jose Reyes
* Project 2
* 11/12/2022
* UMGC
* CMSC 350
* Polynomial class compares polynomials as well as iterates terms of the polynomial from
the highest exponent to lowest and returns and an object that contains only the coefficient and exponent of
the next term
*/

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;


public class Polynomial implements Iterable<Polynomial.Poly>, Comparable<Polynomial> {

    Comparator<Polynomial> compared;
    private Poly head;



    public Polynomial(String fromFile) {
        head = null; // explicitly stating for clarity
        Scanner polyScanner = new Scanner(fromFile);
        try{
            while(polyScanner.hasNext()){
                addPoly(polyScanner.nextDouble(), polyScanner.nextInt());
            }
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            throw new InvalidPolynomialSyntax("Error: Incorrect Syntax, please check inputs!");
        }
    }

   /*
method: addPoly
arguments: double coefficient and integer exponent
if head is null, new term is null, Otherwise sets new term to next null term.next
*/
    public void addPoly(double coefficient, int exponent ){
        if (exponent < 0){
            throw new InvalidPolynomialSyntax("Error: No negative exponents, please check inputs!");
        }
        Poly current = head;
        if(current == null){ // then Polynomial is empty
            head = new Poly(coefficient, exponent);
            head.next = null;
        } else { //find end by looping to null next link
            while(current.next != null){
                current = current.next;
            }
            current.next = new Poly(coefficient, exponent);
        }

    }
    /*
method: overridden compareTo via Comparable
Arguments: Another Polynomial object
*/
    @Override
    public int compareTo(Polynomial otherPoly) {
        Poly thisCurrent = this.head;
        Poly otherCurrent = otherPoly.head;

        while (thisCurrent != null && otherCurrent != null){
// its positive if this is larger
            if (thisCurrent.getExponent() != otherCurrent.getExponent()){
                return thisCurrent.getExponent() - otherCurrent.getExponent();
// simple checking for larger
            }else if(thisCurrent.getCoefficient() != otherCurrent.getCoefficient()) {
                if(otherCurrent.getCoefficient()> thisCurrent.getCoefficient()){
                    return -1;
                }else if(otherCurrent.getCoefficient()< thisCurrent.getCoefficient()){
                    return +1;
                }
            }
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }
        if (thisCurrent == null && otherCurrent == null){
            return 0;
        }//this would be the case of one with more terms than other
        if (thisCurrent == null){
            return -1;
        }else {
            return +1;
        }
    }
 /*
method: compareExponents / Arguments: Another Polynomial object
description: compares exponents in two polynomials, is called to check weak order

    */
    public int compareExponents(Polynomial poly2) {
        Poly thisPolyTerm = this.head;
        Poly otherPolyTerm = poly2.head;
        while(thisPolyTerm != null && otherPolyTerm != null) {
            if (thisPolyTerm.getExponent() != otherPolyTerm.getExponent()) {
                return thisPolyTerm.getExponent() - otherPolyTerm.getExponent();
            }else {
                thisPolyTerm = thisPolyTerm.getNext();
                otherPolyTerm = otherPolyTerm.getNext();
            }
        }if(thisPolyTerm == null && otherPolyTerm == null){
            return 0;
        }
        if (otherPolyTerm == null){
            return +1;
        }else {
            return -1;
        }
    }
    public Polynomial() { compared = (Polynomial poly1, Polynomial poly2) -> poly1.compareExponents(poly2); }
    public Polynomial( Comparator<Polynomial> compare){ this.compared = compare; }

  /*
method: overridden Iterator via Iterable
produces an iterator that can traverse terms of a polynomial
*/
    @Override
    public Iterator<Poly> iterator() {
        return new Iterator() {

            private Poly current = getHead();

            @Override
            public boolean hasNext() {
                return current != null && current.getNext() != null;
            }

            @Override
            public Poly next() {
                Poly data = current;
                current = current.next;
                return data;
            }
        };
    }
    /*
method: overridden toString
adds the operator based off coefficient sign.
 */

    @Override
    public String toString() {
        StringBuilder exBuilder = new StringBuilder();
//check head
        if (head.coefficient > 0){
            exBuilder.append(head.toString());
        }else{
            exBuilder.append(" - ").append(head.toString());
        }//check the other nodes
        for(Poly tmp = head.next; tmp != null; tmp = tmp.next) {
            if (tmp.coefficient < 0) {
                exBuilder.append(" - ").append(tmp.toString());
            } else {
                exBuilder.append(" + ").append(tmp.toString());
            }
        }
        return exBuilder.toString();

    }
/*

Nested Class: Poly
overridden toString nodes of the polynomial objects which cary the reference to the next node

*/
    static class Poly{
        private double coefficient;
        private int exponent;
        private Poly next;

        private Poly(double c, int e) {
            coefficient = c;
            exponent = e;
            next = null;
        }

        private int getExponent(){
            return this.exponent;
        }
        private double getCoefficient(){
            return this.coefficient;
        }
        private Poly getNext(){
            return next;
        }

        @Override
        public String toString(){
            String polyString = String.format("%.1f", Math.abs(coefficient));
            if (exponent == 0) {
                return polyString;
            }else if(exponent == 1){
                return polyString + "x";
            } else{
                return polyString + "x^" + exponent;
            }
        }
    }

    private Poly getHead() {
        return head;
    }
}
