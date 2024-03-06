<template>
    <div style="padding:10px;display: flex;justify-content: center;">
        <div class="loader"></div>
    </div>
</template>

<script>
export default {
    name: "CheckMark"
}
</script>

<style scoped>
/*圆圈*/
.loader {
    position: relative;
    width: 80px;
    height: 80px;
    border-radius: 50%;
    border: 4px solid rgba(165, 220, 134, 0.2);
    border-left-color: #A5DC86;
    animation: animation_collect 0.5s linear 1 both;
    /*animation: animation_collect 1s linear infinite;*/
}

/*圆圈动画*/
@keyframes animation_collect {
    0% {
        transform: rotate(270deg);
        border-left-color: #A5DC86;
    }
    25% {
        border-left-color: #A5DC86;
    }
    50% {
        border-left-color: #A5DC86;
    }
    75% {
        border-left-color: #A5DC86;
    }
    100% {
        border-left-color: rgba(165, 220, 134, 0.2);
        transform: rotate(0deg);
    }
}

/*对号*/
.loader::before {
    position: absolute;
    content: '';
    top: 50%;
    left: 15px;
    border: 4px solid #A5DC86;
    border-left-width: 0;
    border-bottom-width: 0;
    transform: scaleX(-1) rotate(135deg);
    transform-origin: left top;
    /*动画延迟*/
    animation: animation_true 0.5s 0.5s linear 1 both;
}

@keyframes animation_true {
    0% {
        opacity: 0;
        width: 0;
        height: 0;
    }
    33% {
        opacity: 1;
        width: 20px;
        height: 0;
    }
    100% {
        opacity: 1;
        width: 20px;
        height: 40px;
    }
}
</style>