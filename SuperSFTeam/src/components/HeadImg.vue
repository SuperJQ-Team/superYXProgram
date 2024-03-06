<template>
    <img :class="{'head-img':true, 'is-hide':isHide}" src="../icons/headimg.jpg" alt="寄"/>
</template>

<script>
export default {
    name: "HeadImg",
    data() {
        return {
            isHide: true,
        }
    },
    mounted() {
        setTimeout(() => {
            this.isHide = false;
        }, 0);
    },
}
</script>

<style scoped>
.head-img {
    opacity: 1;
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: 0.5s ease-in-out;
}

.is-hide {
    opacity: 0;
}
</style>