            function change_s(id) {
                var span = document.getElementById(id);
                span.style.border = "2px inset";
            }
            function recoberstyle(id,href) {
                var span = document.getElementById(id);
                span.style.border = "2px outset";
                var ifr = document.getElementById("of");
                ifr.src = href;
            }
            function closeframemoused() {
                var img = document.getElementById("imges");
                img.style.border = "2px inset";
            }
            function closeframemouseu() {
                var img = document.getElementById("imges");
                img.style.border = "2px outset";
                var ifr = document.getElementById("of");
                ifr.src = "";
            }