# Web app for displaying data from HDC1080

Datasheet for HDC1080 can be found here:

[Link](https://www.ti.com/lit/ds/symlink/hdc1080.pdf?ts=1620137207479&ref_url=https%253A%252F%252Fwww.ti.com%252Fproduct%252FHDC1080)

# Used technologies
* Java 11, MySQL, Thymeleaf, HTML, CSS, Google Charts.

# How it works
This app connects with an external MySQL database installed on Raspberry Pi. From there it reads temperature and humidity data as well as measurement timestamps. The data is then displayed in a table (last 60 measurements). The temperature and humidity from last 6h and last 24h can also be displayed on subsequent pages.

