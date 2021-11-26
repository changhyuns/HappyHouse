<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div class="mt-2 mb-5" style="text-align: center; color: #170B3B; font-weight: bold; font-size: 20px;">
          게시글 수정 <b-icon icon="journal-bookmark-fill" animation="throb" font-scale="2" style="margin-left: 10px"></b-icon>
        </div>
      </b-col>
    </b-row>
    <board-write-form type="modify" />
  </b-container>
</template>

<script>
import BoardWriteForm from "./child/BoardWriteForm.vue";

export default {
  name: "BoardUpdate",
  components: {
    BoardWriteForm,
  },
};
</script>

<style></style>
