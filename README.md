# Polynomial Order Checker

## Introduction

This project, created by Jose Reyes for the CMSC 350 course at UMGC, is a Java-based application designed to read polynomial expressions from a file and determine if the list of polynomials is in weak or strong order. The project uses a graphical file chooser to select the input file and provides output via the console.

## Table of Contents

- [Usage](#usage)
- [Features](#features)
- [Dependencies](#dependencies)
- [Configuration](#configuration)
- [Documentation](#documentation)
- [Examples](#examples)
- [Troubleshooting](#troubleshooting)
- [Contributors](#contributors)
- [License](#license)

## Usage

1. **Run the application:**

    ```bash
    java -cp bin Main
    ```

2. **Select the file containing polynomial expressions when prompted by the file chooser dialog.**

## Features

- **File Selection:** Uses `JFileChooser` to select files and directories.
- **Polynomial Parsing:** Reads and parses polynomial expressions from a file.
- **Order Checking:** Determines if the polynomials are in strong or weak order.
  - **Strong Order:** Polynomials are in a sorted order.
  - **Weak Order:** Checks if the exponents are in a non-decreasing order.

## Dependencies

- Java Development Kit (JDK) 8 or higher.
- Swing library for graphical user interface.

## Configuration

No additional configuration is required for this project.

## Documentation

### Classes

- **Main:** Entry point of the application, contains methods for file processing and order checking.
- **Polynomial:** A class representing a polynomial expression.
- **OrderedList:** Utility class for checking if a list of polynomials is sorted.

### Methods

- **Main Class:**
  - **main(String[] args):** Starts the application and processes the polynomial list.
  - **fromFile():** Uses `JFileChooser` to select a file and reads polynomial expressions into an `ArrayList`.
  - **checkWeakOrder(List<Polynomial> polyList):** Checks if the polynomials are in weak order.
  - **processPolyList():** Processes the list of polynomials, checks their order, and prints the results.

- **OrderedList Class:**
  - **checkSorted(List<T> list):** Determines whether a list is in strictly ascending order.
  - **checkSorted(List<T> list, T current):** Helper method to compare current and next elements in the list.

- **Polynomial Class:**
  - **Polynomial(String fromFile):** Constructor that parses a polynomial string and initializes the polynomial terms.
  - **addPoly(double coefficient, int exponent):** Adds a term to the polynomial.
  - **compareTo(Polynomial otherPoly):** Compares this polynomial to another polynomial.
  - **compareExponents(Polynomial poly2):** Compares the exponents of two polynomials.
  - **iterator():** Returns an iterator to traverse the polynomial terms.
  - **toString():** Returns a string representation of the polynomial.

- **Polynomial.Poly Nested Class:**
  - **Poly(double c, int e):** Constructor to create a polynomial term.
  - **getExponent():** Returns the exponent of the term.
  - **getCoefficient():** Returns the coefficient of the term.
  - **getNext():** Returns the next term in the polynomial.
  - **toString():** Returns a string representation of the polynomial term.

## Examples

- **Example Polynomial File:**
    ```
    3x^2 + 2x + 1
    5x^3 + 4x^2 + x + 6
    2x^2 + 3
    ```

- **Expected Console Output:**
    ```
    3x^2 + 2x + 1
    5x^3 + 4x^2 + x + 6
    2x^2 + 3
    Strong Ordered: true/false
    Weak Ordered: true/false
    ```

## Troubleshooting

- **File Not Found:** Ensure the file selected exists and is readable.
- **Invalid Polynomial Syntax:** Ensure the polynomial expressions in the file are correctly formatted.

## Contributors

- **Jose Reyes**

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
