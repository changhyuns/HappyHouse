<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div class="mt-2 mb-5" style="text-align: center; color: #170B3B; font-weight: bold; font-size: 20px;">
          공지사항 수정 <b-icon icon="file-earmark-font" font-scale="2" style="margin-left: 10px"></b-icon>
        </div>
      </b-col>
    </b-row>
    <notice-write-form type="modify" />
  </b-container>
</template>

<script>
import NoticeWriteForm from "./child/NoticeWriteForm.vue";

export default {
  name: "NoticeUpdate",
  components: {
    NoticeWriteForm,
  },
};
</script>

<style></style>
