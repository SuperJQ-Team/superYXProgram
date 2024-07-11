<template>
    <div :class="{'nav-ball':true, 'team-id-cover-begin':TeamIdCoverBegin, 'team-id-cover-hide':TeamIdCoverHide}">
        <img class="logo-img" src="../icons/headimg.jpg" alt="寄"/>
        <div :class="{'catalogue':true,'catalogue-show':showCatalogue}">
            <p>{{ PageNumber ? NavList[PageNumber - 1].catalogue : null }}</p>
        </div>
        <div class="cover-radius"></div>
        <ul>
            <li v-for="(item, index) in NavList" :class="GetActiveState(index, PageNumber)" :key="index">
                <p class="nav-title">{{ item.title }}</p>
                <p :class="{'nav-text':true,'nav-text-hide':GetActiveState(index,PageNumber) !== 'ball-page-active'}">
                    {{ item.text }}</p>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: "NavBall",
    components: {},
    data() {
        return {
            TeamIdCoverBegin: true,
            TeamIdCoverHide: false,
            showCatalogue: false,
            PageNumber: 0,
            NavList: [
                {
                    title: "团队介绍",
                    text: "_____，_____！",
                    catalogue: "团队介绍",
                },
                {
                    title: "超高校级的卷王——LSF",
                    text: "传奇爆肝王",
                    catalogue: "团队成员",
                },
                {
                    title: "超高校级的前端人员——LJQ",
                    text: "没合适的照片就p了一张",
                    catalogue: "团队成员",
                },
                {
                    title: "超高校级的资源管理大师——ZYX",
                    text: "照片他拍的",
                    catalogue: "团队成员",
                },
                {
                    title: "超高校级的技术支持人员——ZHL",
                    text: "PPT速通记录保持者",
                    catalogue: "团队成员",
                },
                {
                    title: "宇宙安全声明",
                    catalogue: "免责声明",
                },
            ]
        }
    },
    methods: {
        SetCoverHide(bool) {
            this.TeamIdCoverHide = bool;
        },
        SetPage(n) {
            let animationTime = 0;
            if (this.PageNumber === 0 && n === 1) animationTime = 350;
            if (this.PageNumber === 1 && n === 0) this.showCatalogue = false;
            else {
                setTimeout(() => {
                    this.showCatalogue = true;
                }, 300);
            }
            setTimeout(() => {
                this.PageNumber = n;
            }, animationTime);
        },
        GetActiveState(idx, pag) {//li下标、当前页数
            idx = idx + 1;
            if (pag === 0) {
                return "ball-page-hide";
            } else if (idx === pag) {
                return "ball-page-active";
            } else if (idx === pag - 1) {
                return "ball-page-top-side";
            } else if (idx === pag + 1) {
                return "ball-page-bottom-side";
            } else if (idx === pag + 2) {
                return "ball-page-bottom-hide";
            } else if (idx === pag - 2) {
                return "ball-page-top-hide";
            } else if (idx > pag + 2) {
                return "ball-page-bottom-overflow";
            } else if (idx < pag - 2) {
                return "ball-page-top-overflow";
            } else return "unknown";
        }
    },
    mounted() {
        setTimeout(() => {
            this.TeamIdCoverBegin = false;
        }, 0);
    }
}
</script>

<style scoped>
.nav-ball {
    position: absolute;
    z-index: 200;
    padding: 0;
    right: calc(50vw + 40px);
    bottom: 25vh;
    transition: 1s cubic-bezier(.30, .05, .15, 1.0);
}

.nav-ball {
    border-radius: 50%;
    border-width: 0;
    background-color: rgba(250, 250, 250, 0.8);
}

/*logo图片*/
.logo-img {
    height: 100%;
    max-height: 50vh;
    border-radius: 50%;
    border-width: 0;
    vertical-align: bottom;
    z-index: 300;
    transition: 1s cubic-bezier(.30, .05, .15, 1.0);
}

.logo-img {
    width: 100%;
    max-width: 40vw;
    border-radius: 50%;
}

.catalogue {
    position: absolute;
    z-index: -1;
    border-radius: 50%;
    border-width: 0;
    overflow: hidden;
    margin: auto;
    right: 50%;
    top: 0;
    left: 0;
    bottom: 0;
    transition: 1s;
    opacity: 0;
}

.catalogue-show {
    opacity: 1;
}

.catalogue p {
    margin: 0;
    font-size: 25px;
    align-content: center;
    text-align: center;
    line-height: 50vh;
    transition: 0.8s;
}

.cover-radius {
    position: absolute;
    height: 100%;
    max-height: 50vh;
    border-radius: 50%;
    border-width: 0;
    vertical-align: bottom;
    width: 100%;
    max-width: 40vw;
    z-index: -2;
    background-color: rgba(240, 240, 240, 1);
    transform: translate(0, -100%);
    transition: 1s cubic-bezier(.30, .05, .15, 1.0);
}

/*在靠边、隐藏时logo图片也透明*/
.team-id-cover-hide .logo-img {
    opacity: 0;
}

.team-id-cover-begin .logo-img {
    opacity: 0;
}

.nav-ball ul {
    position: absolute;
    left: 20vh;
    top: 50%;
    list-style: none;
}

.nav-ball li {
    left: 0;
    position: absolute;
    float: left;
    /*background: linear-gradient(to left, rgba(255, 192, 203, 0.5) 30%, rgba(255, 192, 203, 0));*/
    background-color: rgba(40, 40, 40, 0);
    width: 400px;
    z-index: -2;
    --rotate-move: 30vh;
    transition: 0.8s;
    border-top: 3px solid rgba(255, 255, 255, 0);
    /*border-bottom: 3px solid rgba(255, 255, 255, 0.7);*/
}

.nav-ball ul li p.nav-title {
    margin-left: 10px;
}

.nav-ball ul li p.nav-text {
    margin-left: 10px;
    font-size: 20px;
    transition: 0.8s;
}

.nav-ball ul li p.nav-text.nav-text-hide {
    font-size: 12px;
    color: rgba(255, 255, 255, 0);
}

.nav-ball li.ball-page-hide {
    left: 100vw;
    width: 5vh;
    height: 5vh;
    border-radius: 50%;
    transform: translate(0, -50%);
    transition: 1.2s;
    border-width: 0;
    color: rgba(255, 255, 255, 0);
    border-top: 3px solid rgba(255, 255, 255, 0);
}

.nav-ball li.ball-page-top-hide {
    z-index: -5;
    width: 80vh;
    height: 6.2vh;
    color: rgba(255, 255, 255, 0.2);
    border-top: 3px solid rgba(255, 255, 255, 0.2);
    transform: translate(-100%, -50%) rotate(180deg) translate(0, var(--rotate-move)) rotate(-180deg) translate(50vh, 0);
}

.nav-ball li.ball-page-top-side {
    z-index: -4;
    width: 90vh;
    height: 9.4vh;
    color: rgba(255, 255, 255, 0.6);
    border-top: 3px solid rgba(255, 255, 255, 0.6);
    transform: translate(0, -1vh) translate(-100%, -50%) rotate(135deg) translate(0, var(--rotate-move)) rotate(-135deg) translate(50vh, 0);
}

.nav-ball li.ball-page-active {
    z-index: -3;
    width: 100vh;
    height: 35vh;
    font-size: 25px;
    color: rgba(255, 255, 255, 1);
    border-top: 3px solid rgba(255, 255, 255, 1);
    transform: translate(-100%, -50%) rotate(90deg) translate(0, var(--rotate-move)) rotate(-90deg) translate(50vh, 0);
}

.nav-ball li.ball-page-bottom-side {
    z-index: -4;
    width: 90vh;
    height: 9.4vh;
    color: rgba(255, 255, 255, 0.6);
    border-top: 3px solid rgba(255, 255, 255, 0.6);
    transform: translate(0, 1vh) translate(-100%, -50%) rotate(45deg) translate(0, var(--rotate-move)) rotate(-45deg) translate(50vh, 0);
}

.nav-ball li.ball-page-bottom-hide {
    z-index: -5;
    width: 80vh;
    height: 6.2vh;
    color: rgba(255, 255, 255, 0.2);
    border-top: 3px solid rgba(255, 255, 255, 0.2);
    transform: translate(-100%, -50%) rotate(0deg) translate(0, var(--rotate-move)) rotate(0deg) translate(50vh, 0);
}

.nav-ball li.ball-page-top-overflow {
    z-index: -6;
    width: 80vh;
    height: 6.2vh;
    color: rgba(255, 255, 255, 0);
    border-top: 3px solid rgba(255, 255, 255, 0);
    transform: translate(-100%, -50%) rotate(180deg) translate(0, var(--rotate-move)) rotate(-180deg) translate(50vh, 0);
}

.nav-ball li.ball-page-bottom-overflow {
    z-index: -6;
    width: 80vh;
    height: 6.2vh;
    color: rgba(255, 255, 255, 0);
    border-top: 3px solid rgba(255, 255, 255, 0);
    transform: translate(-100%, -50%) rotate(0deg) translate(0, var(--rotate-move)) rotate(0deg) translate(50vh, 0);
}

@media screen and (max-width: 950px) {
    .team-id-cover, .nav-ball {
        right: 30vw;
        bottom: calc(50vh - 120px);
    }

    .team-id-cover-hide, .team-id-cover-begin {
        bottom: -50vw;
    }
}

</style>
