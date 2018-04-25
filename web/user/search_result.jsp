<%-- 
    Document   : video_list
    Created on : Mar 14, 2018, 4:40:35 PM
    Author     : Tito
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="../css/style.css" type="text/css">
        <link rel="stylesheet" href="../pink.flag/css/jplayer.pink.flag.css" type="text/css">  
        <script type="text/javascript" src="../js/jquery.min.js"></script>
        <script type="text/javascript" src="../js/jquery.jplayer.min.js"></script>
        <script src="../js/index.js"></script>
        <script src="../js/jplayer.video.js"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>
        <title>Lista de Videos</title>
    </head>
    <body>
        <h1>Lista de Videos</h1>
        <div>
            <table class="rwd-table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Título</th>
                        <th>Autor</th>
                        <th>Estreno</th>
                        <th>Descripción</th>
                        <th>Reproducciones</th>
                        <th>Duración</th>
                        <th>Formato</th>
                        <th>Video</th>
                        <th>Usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${videos}" var="video">
                        <tr>
                            <td data-th="Id">${video.id}</td>
                            <td data-th="Título">${video.title}</td>
                            <td data-th="Autor">${video.author}</td>
                            <td data-th="Estreno"> <fmt:formatDate value="${video.releaseDate.toGregorianCalendar().time}" pattern="dd-MM-yyyy" />
                            </td>
                            <td data-th="Descripción">${video.description}</td>
                            <td data-th="Reproducciones">${video.reproductions}</td>
                            <td data-th="Duración">${video.duration}</td>
                            <td data-th="Formato">${video.format}</td>
                            <td data-th="Video">                    
                                <a href="${video.url}" class="link new-user" target="_blank">
                                    <c:if test="${not empty video.url}">
                                    <span><i class="fas fa-play"></i></span>
                                    </c:if>
                                </a>
                            </td>
                            <td data-th="Usuario">${video.getUserId()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
                <div class="separator">
            <ul>
                <li>
                    <a href="/VideoManager/user/search_videos.jsp" class="link new-user">
                        <span><i class="fas fa-upload"></i></span>
                        <span class="link-text">Buscar un nuevo video</span>
                    </a>
                </li>
                <li>
                    <a href="/VideoManager/user/index.html" class="link new-user">
                        <span><i class="fas fa-home"></i></span>
                        <span class="link-text">Volver al inicio</span>
                    </a>
                </li>
            </ul>
        </div>
        
        
        <div id="jp_container_1" class="jp-video " role="application" aria-label="media player">
  <div class="jp-type-single">
    <div id="jquery_jplayer_1" class="jp-jplayer"></div>
    <div class="jp-gui">
      <div class="jp-video-play">
        <button class="jp-video-play-icon" role="button" tabindex="0">play</button>
      </div>
      <div class="jp-interface">
        <div class="jp-progress">
          <div class="jp-seek-bar">
            <div class="jp-play-bar"></div>
          </div>
        </div>
        <div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
        <div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
        <div class="jp-details">
          <div class="jp-title" aria-label="title">&nbsp;</div>
        </div>
        <div class="jp-controls-holder">
          <div class="jp-volume-controls">
            <button class="jp-mute" role="button" tabindex="0">mute</button>
            <button class="jp-volume-max" role="button" tabindex="0">max volume</button>
            <div class="jp-volume-bar">
              <div class="jp-volume-bar-value"></div>
            </div>
          </div>
          <div class="jp-controls">
            <button class="jp-play" role="button" tabindex="0">play</button>
            <button class="jp-stop" role="button" tabindex="0">stop</button>
          </div>
          <div class="jp-toggles">
            <button class="jp-repeat" role="button" tabindex="0">repeat</button>
            <button class="jp-full-screen" role="button" tabindex="0">full screen</button>
          </div>
        </div>
      </div>
    </div>
    <div class="jp-no-solution">
      <span>Update Required</span>
      To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
    </div>
  </div>
</div>
        
    </body>
</html>
