#include "CMatrix.h"

CMatrix::CMatrix() {
    row = column = 0;
    line = 0L;
    scanLine = 0L;
}

CMatrix::~CMatrix() {
    // Fill in codes here
}

CMatrix::CMatrix(int row, int column, double init_value) {
    this->row = row;
    this->column = column;
    scanLine = new double *[row];
    scanLine[0] = line = new double[row * column];
    double *p = scanLine[0] + column;
    for (int i = 1; i < row; i++, p += column)
        scanLine[i] = p;
    p = line;
    for (int i = 0, size = row * column; i < size; i++, p++)
        *p = init_value;
}

CMatrix::CMatrix(const CMatrix &right) {
    // Fill in codes here
}

CMatrix &CMatrix::operator+(const CMatrix &right) const {
    // Fill in codes here
}

CMatrix &CMatrix::operator-(const CMatrix &right) const {
    // Fill in codes here
}

CMatrix &CMatrix::operator-() const {
    // Fill in codes here
}

CMatrix &CMatrix::operator*(const CMatrix &right) const {
    // Fill in codes here
}

CMatrix &CMatrix::operator/(double scale) const {
    CMatrix &m = this->clone();
    if (scale == 0.0) return m;
    double *p = m.line;
    for (int i = 0, size = (row * column); i < size; i++, p++)
        *p /= scale;
    return m;
}

CMatrix &CMatrix::operator[](int col) const {
    CMatrix &m = *new CMatrix(row, 1);
    double *p = m.line;
    for (int i = 0; i < row; i++, p++)
        *p = scanLine[i][col];
    return m;
}

// returns the column vector
CMatrix &CMatrix::operator~() const {
    // Fill in codes here

}

// CMatrix transpose
CMatrix &CMatrix::clone() const {
    CMatrix &m = *new CMatrix(*this);
    return m;
}

double &CMatrix::operator()(int r, int c) const {
    // Fill in codes here
}

double CMatrix::max() const {
    // Fill in codes here
}

double CMatrix::min() const {
    // Fill in codes here
}

ostream &operator<<(ostream &os, const CMatrix &m) {
    os << endl << "row=" << m.row << ",column=" << m.column;
    double *p = m.line;
    for (int i = 0; i < m.row; i++) {
        os << endl;
        for (int j = 0; j < m.column; j++, p++)
            os << *p << " ";
    }
    return os;
}