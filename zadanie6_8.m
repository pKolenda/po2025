close all; clear all; 

% wzór: Cn+1 = 2xCn - Cn-1


C = cell(11, 1); % Wektor komórek do przechowywania wielomianów C0 do C10
C{1} = [ 1 ];
C{2} = [ 1, 0 ];

for m = 2:10
    C_m = C{m};
    A = 2 * C_m;
    A = [A, 0];
    C_minus_1 = C{m-1};
    B = C_minus_1;
    roznica_dl = length(A) - length(B);
    new_B = [zeros(1, roznica_dl), B];
    C{m+1} = A - new_B;

end

x = linspace(-1, 1, 500);
figure;
hold on; 
grid on;
title('Wielomiany Czebyszewa C_0(x) do C_10(x) dla x \in [-1, 1]');
xlabel('x');
ylabel('C_n(x)');

for n = 0:10
    Cn_uzyt = C{n+1};
    y = polyval(Cn_uzyt, x);    % obliczanie funkcji
    
    % Rysuj linię
    plot(x, y, 'DisplayName', ['C_', num2str(n), '(x)'], 'LineWidth', 1.5);
end

legend('show', 'Location', 'SouthEast');
xlim([-1, 1]);
ylim([-1, 1]); 
hold off;