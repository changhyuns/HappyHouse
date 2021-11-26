<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div style="text-align: center; color: #170B3B; font-weight: bold; font-size: 20px;">- 작성</div>
      </b-col>
    </b-row>
    <notice-write-form type="register" />
  </b-container>
</template>

<script>
import NoticeWriteForm from "./child/NoticeWriteForm.vue";

export default {
  name: "NoticeWrite",

  components: {
    NoticeWriteForm,
  }

};
</script>

<style></style>
