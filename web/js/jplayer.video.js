/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 $(document).ready(function(){
    $("#jquery_jplayer_1").jPlayer({
      cssSelectorAncestor: "#jp_container_1",
      swfPath: "/js",
      supplied: "mp4, m4v, ogv",
      useStateClassSkin: true,
      autoBlur: false,
      smoothPlayBar: true,
      keyEnabled: true,
      remainingDuration: true,
      toggleDuration: true
    });
      
    $(".video-play").click(function(event){

        event.preventDefault();
        var video = $(this).data();
        console.log(video);
        $("#jquery_jplayer_1").jPlayer("setMedia", video).jPlayer("play");
        $("html, body").animate({ scrollTop: 0 }, "slow");
    });
});
   
