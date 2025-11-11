close all; clear all; 

% C3: 4x^3 - 3x
% C4: 8x^4 - 8x^2 +1 
% wzór: Cn+1 = 2xCn - Cn-1


C = cell(8, 1); % Wektor komórek do przechowywania wielomianów C0 do C7
C{1} = [ 1 ];           %C0
C{2} = [ 1, 0 ];        %C1
C{3} = [ 2, 0, -1 ];    %C2
C{4} = [4, 0, -3, 0];   %C3
C{5} = [8, 0, -8, 0, 1]; %C4

for n = 5:7
    A = 2 * C{n};
    A = [A, 0];
    B = C{n-1};
    roznica_dl = length(A) - length(B);
    new_B = [zeros(1, roznica_dl), B];
    C{n+1} = A - new_B;

end

x = linspace(-1, 1, 500);
figure;
hold on; 
grid on;
title('Wielomiany Czebyszewa C_0(x) do C_7(x) dla x \in [-1, 1]');
xlabel('x');
ylabel('C_n(x)');

for n = 0:7
    Cn_uzyt = C{n+1};
    y = polyval(Cn_uzyt, x);
    
    % Rysuj linię
    plot(x, y, 'DisplayName', ['C_', num2str(n), '(x)'], 'LineWidth', 1.5);
end

legend('show', 'Location', 'SouthEast');
xlim([-1, 1]);
ylim([-1, 1]); 
hold off;