# Web app for displaying data from HDC1080

Datasheet for HDC1080 can be found here:

[Link](https://www.ti.com/lit/ds/symlink/hdc1080.pdf?ts=1620137207479&ref_url=https%253A%252F%252Fwww.ti.com%252Fproduct%252FHDC1080)

# Used technologies
* Java 11, MySQL, Thymeleaf, HTML, CSS, Google Charts.

# How it works
Data from HDC1080 is read with Raspberry Pi, using Python3 library made by user  [switchdoclabs](https://github.com/switchdoclabs/SDL_Pi_HDC1080_Python3) Then it is added to MySQL database also installed on Pi.

The web app connects with that MySQL database using Spring Data and JPQL queries. From there it reads temperature and humidity data as well as measurement timestamps. This app uses a line chart from Google Charts which was described [here](https://developers.google.com/chart/interactive/docs/gallery/linechart).

The data is then displayed in a table (last 60 measurements).
![Alt text](SCREENSHOTS/tabela.png?raw=true "Title")

The temperature and humidity from last 6h and last 24h is also displayed on subsequent pages.
Below you can see chart with data from the last 6 hours. 
![Alt text](SCREENSHOTS/wykres6h.png?raw=true "Title")



