#ifndef __CMatrix__
#define __CMatrix__

#include <iostream>

using namespace std;

class CMatrix {
private:
    int row;
    int column;
    double **scanLine;
    double *line;
public:
    CMatrix();

    ~CMatrix();

    CMatrix(int, int, double= 0.0);

    CMatrix(const CMatrix &);

    CMatrix &operator+(const CMatrix &) const;

    CMatrix &operator-(const CMatrix &) const;

    CMatrix &operator-() const;

    CMatrix &operator*(const CMatrix &) const;

    CMatrix &operator/(double) const;

    CMatrix &operator[](int) const; // returns the column vector
    CMatrix &operator~() const; // returns transpose
    double &operator()(int, int) const;

    CMatrix &clone() const;

    double max() const;

    double min() const;

    friend ostream &operator<<(ostream &, const CMatrix &);
};

#endif