clear all; close all;

% W wyniku pomiaru otrzymano nastepujace liczby ( x = numer pomiaru, y = wartosc )
x = [ 1 2 3 4 5 6 7 8 9 10 ];
y = [ 0.912 0.945 0.978 0.997 1.013 1.035 1.057 1.062 1.082 1.097 ];
figure; plot( x, y, 'b*' ); title('y=f(x)'); grid;

% Aproksymacja linia prosta: y = a * x + b
    
                                  % OGOLNIE - rozwiazanie rownania macierzowego (wzór 6.1)
xt = x'; yt = y'; N = length( xt );     % transponowanie x,y -> kolumny 
X = [ xt, ones(N,1) ];                  % macierz X -> kolumna x + kolumna jedynek 
ab = X \ yt;                            % rozwiązanie -> ab = (X^T*X)^-1 * X^T * yt (wzór 6.1)
a1 = ab(1), b1 = ab(2),                   % 
figure; plot( x, y, 'b*', x, a1*x+b1, 'k-' ); title('y=f(x) -> wzór 6.1'); grid;
rmse6_1 = sqrt(mean((y - (a1*x+b1)).^2))

                                   % W TYM PRZYPADKU - na podstawie wyprowadzonych wzorow (wzór 6.17)
xm = mean( x );                         % srednia wartosc wektora x
ym = mean( y );                         % srednia wartosc wektora y
xr = x - xm;                            % wektor x - srednia x (od kazdego elementu)
yr = y - ym;                            % wektor y - srednia y (od kazdego elementu)
a = (xr * yr') / (xr * xr')             % obliczenie wsp a prostej, to samo                                       % inaczej: a = sum( xr .* yr ) / sum( xr .* xr )
b = ym - a * xm    
figure; plot( x, y, 'b*', x, a*x+b, 'k-' ); title('y=f(x) -> wzór 6.17'); grid;
% obliczenie wsp b prostej


p1 = polyfit(x, y, 1);
a_poly1 = p1(1);
b_poly1 = p1(2);
figure; plot( x, y, 'b*', x, a_poly1*x+b_poly1, 'k-' ); title('y=f(x) -> polyfit, n=1'); grid;

p3 = polyfit(x, y, 3);
y3 = polyval(p3, x);   
figure; plot( x, y, 'b*', x, y3, 'k-' ); title('y=f(x) -> polyfit, n=3'); grid;

%błąd dopasowania RMSE 

rmse6_17 = sqrt(mean((y - (a*x+b)).^2))
rmse_poly1 = sqrt(mean((y - (a_poly1*x + b_poly1)).^2))
rmse_poly3 = sqrt(mean((y - y3).^2))