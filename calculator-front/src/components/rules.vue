 <template>
  <v-card
    class="mt-5 mx-auto rules"
    max-width="300"
    white
    max-height="auto"
   
  >
    

    <v-card-title>
{{exp}}   </v-card-title>


<!-- 
    <v-card-actions>
     

      <v-spacer></v-spacer>

      <v-btn
        icon
        @click="show = !show"
      >
        <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
      </v-btn>
    </v-card-actions>

    <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>

        <v-card-text>
   - you can type using the keyboard but consider to write the right symbols or error will be displayed 
        <br>
        - every calcualation is done on server side when you press equal 
        <br>
        - i could have done the calculator like the windows one but i think doing it this way is more practical         </v-card-text>
      </div>
    </v-expand-transition> -->
  </v-card>
</template>

<script>
  export default {
    props:['exp'],

 data(){
     return{
         show:false,
     }
 }
 
 }
</script>
<style scoped>
.rules{
margin-left: 840px;
border-block-end: 10px;
padding-bottom: 5px;
padding-top: 1px;

}
</style>