close all; clear all;
% Parametry
Nstep = 32;

% Wczytanie obrazu
[img, cmap] = imread('Lena512.bmp'); img = double(img);
N = size(img,1);

% Opcjonalna siatka (wyłączona)
if(0)
    for i=Nstep:Nstep:N-Nstep, img(i-1:i+1,1:N) = 255*ones(3,N); end
    for j=Nstep:Nstep:N-Nstep, img(1:N,j-1:j+1) = 255*ones(N,3); end
end
imshow(img, cmap);

% Środek obrazu 
BW = imbinarize(uint8(img), 0.5*max(img(:)));       % zmiana zdjęcia na czarno-białe 
props = regionprops(BW, img, 'WeightedCentroid');   % znalezienie najjaśniejszego punktu 
if ~isempty(props)
    cx = props.WeightedCentroid(1);
    cy = props.WeightedCentroid(2);
else % jeśli nie da się wyznaczyć -> bierzemy auntomatycznie środek obrazu
    cx = N/2 + 0.5;
    cy = N/2 + 0.5;
end

% Współczynniki wielomianu zniekształceń (przykładowe)
a = [ 1.06, -0.0002, 0.000005 ]; 

% Siatka referencyjna na obrazie oryginalnym (punkty przecięć linii)
i = Nstep:Nstep:N-Nstep; j = i;
[I,J] = meshgrid(i,j); % punkty siatki oryginalnej

% Odległości od środka deformacji - oryginalne punkty siatki
r = sqrt( (I - cx).^2 + (J - cy).^2 );

% Oblicz zmienione współrzędne w obrazie zdeformowanym
R = a(1)*r + a(2)*r.^2 + a(3)*r.^3;

% Punkty przecięć linii w obrazie zdeformowanym (x', y')
Xp = cx + (I - cx).* (R ./ r);
Yp = cy + (J - cy).* (R ./ r);

% Usunięcie ewentualnych NaN (r=0 miejsc)
Xp(isnan(Xp)) = cx;
Yp(isnan(Yp)) = cy;

% Interpolacja obrazu - deformacja beczkowa
x = 1:N; y = 1:N;
[X,Y] = meshgrid(x,y);
r_full = sqrt( (X-cx).^2 + (Y-cy).^2 );
R_full = a(1)*r_full + a(2)*r_full.^2 + a(3)*r_full.^3;
Rn = R_full ./ r_full;
Rn(r_full==0) = 1; % unikamy NaN
imgR = interp2(img, (X-cx).*Rn + cx, (Y-cy).*Rn + cy);

figure;
subplot(1,2,1); imshow(img,cmap); title('Oryginal');
hold on
plot(I,J,'g+','MarkerSize',10); % siatka oryginalna
hold off;

subplot(1,2,2); imshow(imgR,cmap); title('Zdeformowany - beczkowo');
hold on
plot(Xp,Yp,'ro','MarkerSize',6); % siatka deformowana
hold off;

% Estymacja zniekształcenia - dopasowanie wielomianu R=f(r)
r_vect = r(:); R_vect = R(:);
aest1 = pinv([r_vect.^1, r_vect.^2, r_vect.^3])*R_vect;
aest1 = [aest1(end:-1:1);0]; % współczynniki wielomianu

% Wyznaczenie odwrotnego wielomianu r=g(R)
r_fit = linspace(0,max(r_vect), 200);
R_fit = polyval(aest1, r_fit);
ainv = polyfit(R_fit, r_fit, 3);

% Korekcja zniekształcenia (odwrócenie deformacji)
Rr = polyval(ainv, r_full);
Rn = Rr ./ r_full;
Rn(r_full==0) = 1;

r_plot = linspace(0, N/2, 200); 
R_plot = polyval(aest1, r_plot); 
r_corrected_plot = polyval(ainv, R_plot); 

figure; 
subplot(1, 2, 1);
plot(r_plot, R_plot, 'r-');
title('Wielomian Zniekształcający: R = f(r)');
xlabel('r (Oryginalny Promień)');
ylabel('R (Zniekształcony Promień)');
grid on;

subplot(1, 2, 2);
plot(R_plot, r_corrected_plot, 'b-'); 
hold on;
plot([0, N/2], [0, N/2], 'k--'); 
hold off;
title('Wielomian Korygujący: r = g(R)');
xlabel('R (Zniekształcony Promień)');
ylabel('r (Skorygowany Promień)');
legend('Korekcja', 'Idealna linia r=R', 'Location', 'SouthEast');
grid on;

imgRR = interp2(imgR, (X-cx).*Rn + cx, (Y-cy).*Rn + cy);

figure;
subplot(1,2,1); imshow(imgR,cmap); title('Wejscie - efekt rybie oko');
subplot(1,2,2); imshow(imgRR,cmap); title('Wyjscie - po korekcie');
colormap gray