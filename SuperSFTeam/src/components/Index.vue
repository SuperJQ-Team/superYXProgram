<template>
  <!--导航球-->
    <NavBall ref="navBall"></NavBall>

  <!--翻页式页面-->
    <full-page :options="options" ref="fullpage" id="fullpage">
        <div class="section">
            <div class="team-id"><h1>Super SF Team</h1></div>
            <div :class="{'team-id-cover':true, 'team-id-cover-begin':TeamIdCoverBegin, 'team-id-cover-hide':TeamIdCoverHide}"></div>
        </div>
        <div class="section">
            <div>
                <h2>团队介绍</h2>
                <br><br>
                <h2>Super SF Team</h2>
                <br>
                <h2>团队始创于公元2021年，是一款由软创自主创立的全新第一人称开放世界冒险团队。</h2>
                <h2>团队创立于一个被称作「青岛大学」的幻想世界，在这里，被导师选中的人将被授予「大作业」，导引爆肝之力。</h2>
                <h2>你将扮演一位名为「死线战士」的神秘角色，在与死线的战斗中邂逅食量各异、胃口独特的同学们。</h2>
                <!--<h2>和他们一起爆肝作业，找回失散的分数——同时，逐步发掘「考查课」的真相。</h2>-->
                <h2>和他们一起爆肝作业，找回失散的分数——同时，逐步发掘「小学期」的真相。</h2>
            </div>
        </div>
        <div class="section">
            <div>
                <h2>团队成员</h2>
                <img src="../assets/juan.jpg" alt="寄"/>
            </div>
        </div>
        <div class="section">
            <div>
                <h2>团队成员</h2>
                <img src="../assets/+7.png" alt="寄"/>
            </div>
        </div>
        <div class="section">
            <div>
                <h2>团队成员</h2>
                <img src="../assets/xiao.jpg" alt="寄"/>
            </div>
        </div>
        <div class="section">
            <div>
                <h2>团队成员</h2>
                <img src="../assets/heng.jpg" alt="寄"/>
            </div>
        </div>
        <div class="section">
            <div>
                <h2>免责声明</h2>
                <br>
                <p>本网站所提供的信息，只供参考之用。</p>
                <p>
                    本网站及其雇员一概毋须以任何方式就任何信息传递或传送的失误、不准确或错误对用户或任何其他人士负任何直接或间接的责任。</p>
                <p>
                    在法律允许的范围内，本网站在此声明,不承担用户或任何人士就使用或未能使用本网站所提供的信息或任何链接或项目所引致的任何直接、间接、附带、从属、特殊、惩罚性或惩戒性的损害赔偿(包括但不限于收益、预期利润的损失或失去的业务、未实现预期的节省)。</p>
                <p>
                    本网站所提供的信息，若在任何司法管辖地区供任何人士使用或分发给任何人士时会违反该司法管辖地区的法律或条例的规定或会导致本网站或其第三方代理人受限于该司法管辖地区内的任何监管规定时，则该等信息不宜在该司法管辖地区供该等任何人士使用或分发给该等任何人士。用户须自行保证不会受限于任何限制或禁止用户使用或分发本网站所提供信息的当地的规定。</p>
                <p>
                    本网站图片，文字之类版权申明，因为网站可以由注册用户自行上传图片或文字，本网站无法鉴别所上传图片或文字的知识版权，如果侵犯，请及时通知我们，本网站将在第一时间及时删除。</p>
                <p>凡以任何方式登陆本网站或直接、间接使用本网站资料者，视为自愿接受本网站声明的约束。</p>
                <br><br>
                <h2>联系我们</h2>
                <p>联系方式：暂无</p>
            </div>
        </div>
    </full-page>
</template>

<script>
import NavBall from "./NavBall.vue";
import {ref} from "vue"

var animationTimeout;
var transitionTimeout;
var animationTime = 600;
var transitionTime = 600;

var that = null;
var lock = {done: false, topLock: false};

export default {
    name: "Index",
    components: {
        NavBall
    },
    data() {
        return {
            TeamIdCoverBegin: true,//第一次加载完时的动画
            TeamIdCoverHide: false,
            options: {
                licenseKey: "OPEN-SOURCE-GPLV3-LICENSE",
                //是否显示导航，默认为false
                navigation: false,
                //是否可以使用键盘方向键导航（上下键翻页），默认为true
                keyboardScrolling: false,
                //每一页幻灯片的内容是否垂直居中，默认为true
                verticalCentered: true,
                //页面滚动速度
                scrollingSpeed: 700,
                //设置每个section顶部的padding，
                paddingTop: "60px",
                //在移动设置中页面敏感性，最大为100，越大越难滑动
                touchSensitivity: 5,
                //是否使用插件滚动方式，设为false后，会出现浏览器自带的滚动条，将不会按页滚动
                autoScrolling: true,
                onLeave: function (origin, destination, direction) {
                    // console.log(lock.done, lock.topLock)
                    if (lock.done) return;
                    //取消之前的计时，因为onLeave触发了很多次，但只留最后一次计时。
                    if (!lock.topLock) {
                        clearTimeout(animationTimeout);
                        clearTimeout(transitionTimeout);
                    }

                    that.$refs.navBall.SetPage(destination.index);
                    // 向上或向下滚动
                    if (direction === 'down') {
                        if (origin.index === 0) {
                            that.TeamIdCoverHide = true;//id覆盖层移动
                            that.$refs.navBall.SetCoverHide(true);//导航球也一起移动

                            // document.querySelector('.team-id-cover').classList.add('team-id-cover-hide');
                            // document.querySelector('.nav-ball').classList.add('team-id-cover-hide');
                            if (!lock.topLock) {
                                lock.topLock = true;
                                animationTimeout = setTimeout(() => {
                                    lock.done = true;
                                    fullpage_api.moveSectionDown();
                                    transitionTimeout = setTimeout(() => {
                                        lock.done = false;
                                        lock.topLock = false;
                                    }, transitionTime);
                                }, animationTime);
                            }
                        } else {
                            lock.done = true;
                            fullpage_api.moveSectionDown();
                            transitionTimeout = setTimeout(() => lock.done = false, transitionTime);
                        }
                    } else if (direction === 'up') {
                        lock.done = true;
                        fullpage_api.moveSectionUp();
                        transitionTimeout = setTimeout(() => lock.done = false, transitionTime);
                        animationTimeout = setTimeout(() => {
                            if (origin.index <= 1) {
                                that.TeamIdCoverHide = false;
                                that.$refs.navBall.SetCoverHide(false);
                                // document.querySelector('.team-id-cover').classList.remove('team-id-cover-hide');
                                // document.querySelector('.nav-ball').classList.remove('team-id-cover-hide');
                            }
                        }, animationTime);
                    }
                    return lock.done;
                }
            },
        };
    },
    mounted() {
        that = this;
        setTimeout(() => {
            this.TeamIdCoverBegin = false;
        }, 0);
    },
    beforeDestroy() {
        this.$refs.fullpage.api.destroy();
    },
    watch: {
        $route(to, from) {
            if (from.path === '/index') this.$refs.fullpage.api.silentMoveTo(1);
        }
    },
    methods: {},
}
</script>

<style scoped>
.section {
    overflow: hidden;
}

/*控制内容宽度*/
.section:not(:first-child) div {
    height: auto;
    max-height: calc(100vh - 80px);
    width: 60vw;
    padding: 0 20px 0 20px;
    overflow: hidden;
}

.section:not(:first-child) img {
    height: 100%;
    max-height: 80vh;
    width: 100%;
    max-width: 60vw;
    /*object-fit: cover;*/
    object-fit: scale-down;
}

.section div {
    color: white;
}

/*首页文字（id）*/
.team-id {
    margin: auto;
    position: absolute;
    top: 0;
    bottom: 0;
    left: calc(50vw + 5px);
    line-height: 100vh;
}

.team-id h1 {
    user-select: none;
    font-size: 60px;
    text-align: center;
    margin: 0;
    color: white;
    white-space: nowrap;
}

/*文字覆盖层和导航球*/
.team-id-cover, .nav-ball {
    position: absolute;
    z-index: 200;
    padding: 0;
    right: calc(50vw + 40px);
    bottom: 25vh;
    transition: 1s cubic-bezier(.30, .05, .15, 1.0);
}

.team-id-cover {
    height: 100vh;
    width: 100vw;
    bottom: 0;
    background-color: rgb(40, 40, 40);
}

/*靠边*/
.team-id-cover-hide {
    right: -25vh;
}

/*隐藏（在第一次加载完成时的出场动画）*/
.team-id-cover-begin {
    right: -60vh;
}

/*在靠边、隐藏时logo图片也透明*/
.team-id-cover-hide .logo-img {
    opacity: 0;
}

.team-id-cover-begin .logo-img {
    opacity: 0;
}

/*宽度小于950px时的css*/
@media screen and (max-width: 950px) {
    .section:not(:first-child) div {
        height: auto;
        width: auto;
    }

    .team-id {
        left: 0;
        right: 0;
        bottom: 0;
        top: 0;
        line-height: normal;
    }

    .team-id h1 {
        position: absolute;
        margin: auto;
        left: 0;
        right: 0;
        top: calc(50vh + 150px);
        height: auto;
        width: auto;
    }

    .team-id-cover, .nav-ball {
        right: 30vw;
        bottom: calc(50vh - 120px);
    }

    .team-id-cover {
        height: 100vh;
        width: 100vw;
        right: 0;
        left: 0;
        /*一个很酷的边框，虽然没什么用*/
        /*border: 4px solid transparent;*/
        /*border-radius: 16px;*/
        /*background-clip: padding-box, border-box;*/
        /*background-origin: padding-box, border-box;*/
        /*background-image: linear-gradient(to right, #222, #222), linear-gradient(90deg, #8F41E9, #578AEF);*/
    }

    .team-id-cover-begin, .team-id-cover-hide {
        bottom: -50vw;
    }
}

</style>