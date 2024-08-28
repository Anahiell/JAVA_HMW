<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String contextPath = request.getContextPath();
String basicPassword = (String) request.getAttribute("basicPassword");
    String fasol = (String) request.getAttribute("fasol");
    String fileName = (String) request.getAttribute("fileName");
    String otp = (String) request.getAttribute("otp");

%>
<style>
    .image-container {
        text-align: center; /* Центрируем картинки */
        margin-top: 2rem; /* Отступ сверху */
    }

    .image-container img {
        display: block; /* Обеспечиваем, чтобы каждая картинка была на отдельной строке */
        margin: 10px auto; /* Центрируем изображение и задаем отступ */
        max-width: 100%; /* Картинка будет адаптироваться под ширину экрана */
        height: auto; /* Сохраняем пропорции */
    }
</style>
<div class="container mt-5">
    <h2 class="text-center">Hello World!</h2>
    <p class="text-center">Welcome to our website. Here you can find information about our services and database.</p>
    <p class="text-center">Explore the links in the navigation bar to learn more.</p>
</div>
<div>
<h3>EXAmPL of GENERATORS</h3>
    <ul>
        <li><strong>- для імені файлу (символи нижнього реєстру, не містить спец.символів / *?.\)
        </strong>: <%=fileName%></li>
        <li><strong>- для криптографічної солі - без обмежень
        </strong>: <%=fasol%></li>
        <li><strong>- для ОТР (one time password) - тільки цифри з довжиною 6 символів
        </strong>: <%=otp%></li>
        <li><strong>- для постійних паролів - те, що можна набрати з клавіатури (маленькі, великі,
            спец.символи)</strong>: <%=basicPassword%></li>
    </ul>
</div>
<div class="img-container">
    <img src="https://www.pngkey.com/png/detail/964-9648243_gta-sticker-mission-passed-respect-transparent.png" alt="Gta Sticker - Mission Passed Respect Transparent@pngkey.com">
    <img src="<%=contextPath%>/img/complete.webp" alt="complete">
</div>