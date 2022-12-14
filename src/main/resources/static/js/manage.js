const videoDiv = document.querySelector('#video-player');
const videoScreen = document.querySelector('#video-screen');
const queryParams = Object.fromEntries(new URLSearchParams(window.location.search));

let video = "";

fetch('http://localhost:8080/api/v1/video/manage/all?' + new URLSearchParams({token: queryParams.token}))
    .then(result => result.json())
    .then(result => {

        const myVids = document.querySelector('#your-videos');
        if (result.length > 0) {
            for (let vid of result) {
                const li = document.createElement('LI');
                const link = document.createElement('A');
                link.innerText = vid;
                link.className = 'video-elem'
                link.href = window.location.origin + window.location.pathname + '#' + vid;
                li.appendChild(link);
                myVids.appendChild(li);
            }
        } else {
            myVids.innerHTML = 'No videos found';
        }

        document.querySelectorAll('.video-elem').forEach(item => {
            item.addEventListener('click', ev => {
                ev.preventDefault();
                video = ev.target.innerText;
                if (video) {
                    videoScreen.src = `http://localhost:8080/api/v1/video/${video}`;
                    videoDiv.style.display = 'block';
                    document.querySelector('#now-playing')
                        .innerText = 'Now playing ' + video;

                }
            });
        });
    });

