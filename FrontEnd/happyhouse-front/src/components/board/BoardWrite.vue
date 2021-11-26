<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div style="text-align: center; color: #170B3B; font-weight: bold; font-size: 20px;">- 작성</div>
      </b-col>
    </b-row>
    <board-write-form type="register" />
  </b-container>
</template>

<script>
import BoardWriteForm from "./child/BoardWriteForm.vue";

export default {
  name: "BoardWrite",

  components: {
    BoardWriteForm,
  }

};
</script>

<style></style>
