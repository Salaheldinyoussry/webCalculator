<template>
  <v-app class="purple lighten-3">
    <navbar />

    <v-content dark class="black">
      <rules :exp="exp"></rules>

      <v-container
        white
        style="padding-left='400px'"
        class="play elevation-12"
        align="center"
      >
        <v-col cols="12" sm="12" white class="ml-1">
          <v-text-field
            v-model="display"
            hint="Ans"
            label="calc"
            outlined
            class="white display-1"
            max-height="50px"
            readonly
          ></v-text-field>
        </v-col>

        <v-spacer> </v-spacer>
        <v-btn normal x-large dark @click="perc()" fab class=""> %</v-btn>

        <v-btn normal x-large dark @click="sqrt()" fab class="ml-1"> sqr</v-btn>
        <v-btn normal x-large fab dark @click="sqr()" class="ml-1"> x^2</v-btn>
        <v-btn normal x-large fab dark @click="trans()" class="ml-1">
          1/x</v-btn
        >
        <br />
        <v-btn x-large fab white @click="addTodisplay(7)" class="mt-1">
          7</v-btn
        >
        <v-btn
          normal
          x-large
          fab
          white
          @click="addTodisplay(8)"
          class="ml-1 mt-1"
        >
          8</v-btn
        >
        <v-btn
          normal
          x-large
          fab
          white
          @click="addTodisplay(9)"
          class="ml-1 mt-1"
        >
          9</v-btn
        >
        <v-btn normal x-large fab white dark @click="op('/')" class="ml-1 mt-1">
          /</v-btn
        >

        <br />
        <v-btn normal x-large fab @click="addTodisplay(4)" class="s mt-1">
          4</v-btn
        >
        <v-btn normal x-large fab @click="addTodisplay(5)" class="ml-1 mt-1">
          5</v-btn
        >
        <v-btn normal x-large fab @click="addTodisplay(6)" class="ml-1 mt-1">
          6</v-btn
        >
        <v-btn normal x-large fab dark @click="op('*')" class="ml-1 mt-1">
          *</v-btn
        >

        <br />
        <v-btn normal x-large fab @click="addTodisplay(1)" class="mt-1">
          1</v-btn
        >
        <v-btn normal x-large fab @click="addTodisplay(2)" class="ml-1 mt-1">
          2</v-btn
        >
        <v-btn normal x-large fab @click="addTodisplay(3)" class="ml-1 mt-1">
          3</v-btn
        >
        <v-btn normal x-large fab dark @click="op('-')" class="ml-1 mt-1">
          <v-icon dark> mdi-minus</v-icon></v-btn
        >

        <br />
        <v-btn
          normal
          x-large
          dark
          @click="addTodisplay('.')"
          fab
          class="ml-1 mt-1"
        >
          .</v-btn
        >
        <v-btn
          normal
          x-large
          fab
          @click="addTodisplay(0)"
          class="mt-1 .text-h1"
        >
          0</v-btn
        >

        <v-btn normal x-large dark fab @click="changeSign()" class="ml-1 mt-1">
          +/-</v-btn
        >
        <v-btn
          normal
          x-large
          dark
          fab
          @click="op('+')"
          class="ml-1 mt-1 .text-h1"
        >
          +</v-btn
        >
        <br />
        <v-btn
          normal
          x-large
          dark
          @click="clearAll()"
          fab
          class="mt-1 .text-h1"
        >
          c</v-btn
        >
        <v-btn
          normal
          x-large
          dark
          @click="del()"
          fab
          class="ml-1 mt-1 .text-h1"
        >
          <v-icon dark> mdi-keyboard-backspace </v-icon></v-btn
        >

        <v-btn
          normal
          fab
          max-width="90px"
          x-large
          style="width='90px'"
          class="indigo lighten-1 ml-1 mt-1"
          @click="useEql()"
        >
          =</v-btn
        >
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
//import HelloWorld from './components/HelloWorld';
//import Vue from 'vue'
//import axios from 'axios'
//import VueAxios from 'vue-axios'

import navbar from "./components/navbar";
import rules from "./components/rules";

//window.axios=require('axios');
export default {
  name: "App",

  components: {
    navbar,
    rules,
  },

  data() {
    return {
      exp: "",
      a: 0,
      b: 0,
      operatinsCount: 0,
      operation: "",
      ans: 0,
      clear: false,
      display: "",
      pressedOp: "",
      oneOperandOp: false,
      equalFlag: false,
      Errorfreeze: false,
      flag2: false,
      port: "http://localhost:8095/",
      percFlag: false,
      percTemp: 0,
      dotCount: 0,
    };
  },
  methods: {
    calc() {
      this.equalFlag = true;
      this.flag2 = true;
      //  axios.get('http://localhost:8091/23')
      // .then(response => console.log(response));
      var tmp = this.operation;
      if (tmp == "/") {
        tmp = "d";
      }
      if (this.a == "." || this.b == ".") {
        this.display = "E";
        this.Errorfreeze = true;
      } else {
        fetch(this.port + this.a + "/" + tmp + "/" + this.b)
          .then((response) => {
            return response.json();
          })
          .then((data) => {
            this.ans = data.ans;
            if (this.ans == "E") {
              this.Errorfreeze = true;
            }
            this.a = this.ans;
            this.display = this.ans;
          });
      }
    },
    addTodisplay(value) {
      if (
        !this.Errorfreeze &&
        (this.exp[this.exp.length - 1] == "+" ||
          this.exp[this.exp.length - 1] == "-" ||
          this.exp[this.exp.length - 1] == "*" ||
          this.exp[this.exp.length - 1] == "/" ||
          this.exp == "")
      ) {
        this.equalFlag = false;
        this.oneOperandOp = false;

        this.pressedOp = "";
        if (this.clear) {
          this.display = "";
          this.clear = false;
          this.dotCount = 0;
        }
        if (this.dotCount == 0 || (value != "." && this.dotCount >= 1)) {
          if (value == ".") {
            this.dotCount += 1;
          }
          this.display += value;
        }
      }
    },
    op(value) {
      this.percFlag = false;
      this.oneOperandOp = false;
       if (
        this.pressedOp == "" &&
        this.display != "" &&
        this.Errorfreeze == false
      ) {
        this.flag2 = false;
        this.pressedOp = value;

        if (this.operatinsCount == 0) {
          this.a = this.display;
          this.exp += this.display + value;
          this.operatinsCount = 1;
          this.operation = value;
          this.clear = true;
        } else if (this.operatinsCount == 1) {
          this.exp += this.display + value;
          this.clear = true;

          this.b = this.display;
          this.calc();
          this.operation = value;
        } else if (this.operatinsCount == 5) {
          this.a = this.display;
          this.exp += value;
          this.operatinsCount = 1;
          this.operation = value;
          this.clear = true;
        } else if (this.operatinsCount == 6) {
          if (
            this.exp != this.display &&
            this.equalFlag != true &&
            this.operation != ""
          ) {
            this.calc();
            this.display = this.ans;
          } else if (this.equalFlag == true) {
            this.a = this.display;
          }
          this.operatinsCount = 1;
          this.operation = value;
          this.exp += value;

          this.clear = true;
        } else {
          this.exp += value;
          this.operatinsCount = 1;
          this.operation = value;
          this.clear = true;
        }
        if (this.display == ".") {
          this.display = "E";
          this.Errorfreeze = true;
        }
      }
    },

    clearAll() {
      this.equalFlag = false;
      this.oneOperandOp = false;
      this.exp = "";
      this.a = 0;
      this.b = 0;
      this.operatinsCount = 0;
      this.operation = "";
      this.ans = 0;
      this.clear = false;
      this.display = "";
      this.pressedOp = "";
      this.Errorfreeze = false;
      this.flag2 = false;
      this.percFlag = false;
      this.percTemp = 0;
      this.dotCount = 0;
    },
    useEql() {
      if (this.percFlag == true) {
        this.calc();
      } else if (
        this.exp != "" &&
        this.display != "" &&
        this.equalFlag == false &&
        !this.Errorfreeze
      ) {
        this.equalFlag = true;
        this.oneOperandOp = false;

        this.b = this.display;
        this.exp += this.b;

        this.calc();
        this.pressedOp = "";
        this.operatinsCount = 3;
        this.operation = "";
        if (this.b == ".") {
          this.display = "E";
          this.Errorfreeze = true;
        }
      }
    },
    sqrt() {
      if (
        this.Errorfreeze != true &&
        (this.ans == this.display ||
          (this.display == this.b && this.operatinsCount == 5))
      ) {
        this.percFlag = false;

        this.exp = "sqrt(" + this.exp + ")";
        if (this.display == ".") {
          this.display = "E";
          this.Errorfreeze = true;
        } else {
          fetch(this.port + "sqrt/" + this.display)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              this.a = data.ans;
              if (this.a == "E") {
                this.Errorfreeze = true;
              }

              this.display = this.a;
              this.ans = this.a;
              this.b = this.ans;
            });
        }
      } else if (
        !this.percFlag &&
        this.display != "" &&
        !this.Errorfreeze &&
        this.oneOperandOp == false &&
        (this.pressedOp == "" || this.operatinsCount == 0)
      ) {
        this.percFlag = false;

        this.flag2 = true;

        this.equalFlag = true;

        this.pressedOp = "";
        this.oneOperandOp = true;

        this.b = this.display;

        this.exp += "sqrt(" + this.b + ")";
        if (this.b == ".") {
          this.display = "E";
          this.Errorfreeze = true;
        } else {
          fetch(this.port + "sqrt/" + this.b)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              this.b = data.ans;
              if (this.b == "E") {
                this.Errorfreeze = true;
              }

              this.display = this.b;
              if (this.operation != "") {
                if (this.operation == "/") {
                  this.operation = "d";
                }
                fetch(this.port + this.a + "/" + this.operation + "/" + this.b)
                  .then((response) => {
                    return response.json();
                  })
                  .then((data) => {
                    this.ans = data.ans;
                    if (this.ans == "E") {
                      this.Errorfreeze = true;
                    }

                    this.a = this.ans;
                    this.display = this.ans;
                  });
              }
            });
        }

        if (this.operatinsCount == 0) {
          this.operatinsCount = 5;
        } else {
          this.operatinsCount = 4;
        }
      }
    },
    trans() {
      if (
        this.Errorfreeze != true &&
        (this.ans == this.display ||
          (this.display == this.b && this.operatinsCount == 5))
      ) {
        this.percFlag = false;

        this.exp = "1/(" + this.exp + ")";
        if (this.display == ".") {
          this.display = "E";
          this.Errorfreeze = true;
        } else {
          fetch(this.port + "trans/" + this.display)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              this.a = data.ans;
              if (this.a == "E") {
                this.Errorfreeze = true;
              }

              this.display = this.a;
              this.ans = this.a;
              this.b = this.ans;
            });
        }
      } else if (
        !this.percFlag &&
        this.display != "" &&
        this.Errorfreeze != true &&
        this.oneOperandOp == false &&
        (this.pressedOp == "" || this.operatinsCount == 0)
      ) {
        this.percFlag = false;

        this.flag2 = true;

        this.equalFlag = true;

        this.pressedOp = "";
        this.oneOperandOp = true;

        this.b = this.display;

        this.exp += "1/" + this.b;
        if (this.b == ".") {
          this.display = "E";
          this.Errorfreeze = true;
        } else {
          fetch(this.port + "trans/" + this.b)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              this.b = data.ans;
              if (this.b == "E") {
                this.Errorfreeze = true;
              }

              this.display = this.b;
              if (this.operation != "") {
                if (this.operation == "/") {
                  this.operation = "d";
                }
                fetch(this.port + this.a + "/" + this.operation + "/" + this.b)
                  .then((response) => {
                    return response.json();
                  })
                  .then((data) => {
                    this.ans = data.ans;
                    if (this.ans == "E") {
                      this.Errorfreeze = true;
                    }

                    this.a = this.ans;
                    this.display = this.ans;
                  });
              }
            });
        }

        if (this.operatinsCount == 0) {
          this.operatinsCount = 5;
        } else {
          this.operatinsCount = 4;
        }
      }
    },

    sqr() {
      if (
        !this.Errorfreeze &&
        (this.ans == this.display ||
          (this.display == this.b && this.operatinsCount == 5))
      ) {
        this.percFlag = false;

        this.exp = "(" + this.exp + ")^2";
        if (this.display == ".") {
          this.display = "E";
          this.Errorfreeze = true;
        } else {
          fetch(this.port + "sqr/" + this.display)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              this.a = data.ans;
              if (this.a == "E") {
                this.Errorfreeze = true;
              }

              this.display = this.a;
              this.ans = this.a;
              this.b = this.ans;
            });
        }
      } else if (
        !this.percFlag &&
        this.display != "" &&
        !this.Errorfreeze &&
        this.oneOperandOp == false &&
        (this.pressedOp == "" || this.operatinsCount == 0)
      ) {
        this.percFlag = false;

        this.flag2 = true;

        this.equalFlag = true;

        this.pressedOp = "";
        this.oneOperandOp = true;

        this.b = this.display;

        this.exp += this.b + "^2";
        if (this.a == "." || this.b == ".") {
          this.display = "E";
          this.Errorfreeze = true;
        } else {
          fetch(this.port + "sqr/" + this.b)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              this.b = data.ans;
              if (this.b == "E") {
                this.Errorfreeze = true;
              }

              this.display = this.b;
              if (this.operation != "") {
                if (this.operation == "/") {
                  this.operation = "d";
                }
                fetch(this.port + this.a + "/" + this.operation + "/" + this.b)
                  .then((response) => {
                    return response.json();
                  })
                  .then((data) => {
                    this.ans = data.ans;
                    if (this.ans == "E") {
                      this.Errorfreeze = true;
                    }

                    this.a = this.ans;
                    this.display = this.ans;
                  });
              }
            });
        }

        if (this.operatinsCount == 0) {
          this.operatinsCount = 5;
        } else {
          this.operatinsCount = 4;
        }
      }
    },
    del() {
      if (
        this.display != "" &&
        this.equalFlag == false &&
        !this.Errorfreeze &&
        !this.percFlag
      ) {
        if (this.display[this.display.length - 1] == ".") {
          this.dotCount -= 1;
        }
        this.display = this.display.substring(0, this.display.length - 1);
      }
    },
    changeSign() {
      if (
        this.display != "" &&
        this.equalFlag == false &&
        !this.Errorfreeze &&
        !this.percFlag
      ) {
        if (this.display[0] == "-") {
          this.display = this.display.substring(1);
        } else {
          this.display = "-" + this.display;
        }
      }
    },
    perc() {
      var ind = this.exp.lastIndexOf(this.percTemp);

      if (
        !this.Errorfreeze &&
        this.percFlag == true &&
        ind != -1 &&
        this.display == this.percTemp
      ) {
        this.flag2 = true;
        this.clear = false;
        //this.equalFlag = true;
        this.pressedOp = "";
        this.oneOperandOp = true;

        this.exp = this.exp.substring(0, ind);
        var temp;
        if (this.ans == 0) {
          temp = this.a;
        } else {
          temp = this.ans;
        }
        if (temp == "." || this.percTemp == ".") {
          this.display = "E";
          this.Errorfreeze = true;
        } else {
          fetch(this.port + "perc/" + temp + "/" + this.percTemp)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              this.percTemp = data.ans;
              if (this.percTemp == "E") {
                this.Errorfreeze = true;
              }

              this.b = this.percTemp;
              this.display = this.percTemp;
              this.exp += this.percTemp;
            });
        }
      } else if (
        !this.Errorfreeze &&
        this.display != "" &&
        (this.exp[this.exp.length - 1] == "+" ||
          this.exp[this.exp.length - 1] == "-" ||
          this.exp[this.exp.length - 1] == "*" ||
          this.exp[this.exp.length - 1] == "/" ||
          this.exp == "")
      ) {
        this.flag2 = true;
        this.clear = false;
        this.pressedOp = "";
        this.percFlag = true;
        var temp3;
        if (this.ans == 0) {
          temp3 = this.a;
        } else {
          temp3 = this.ans;
        }
        var temp2 = this.display;
        if (temp3 == "." || temp2 == ".") {
          this.display = "E";
          this.Errorfreeze = true;
        } else {
          fetch(this.port + "perc/" + temp3 + "/" + temp2)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              this.percTemp = data.ans;
              if (this.percTemp == "E") {
                this.Errorfreeze = true;
              }

              this.b = this.percTemp;
              this.display = this.percTemp;
              this.operatinsCount = 6;
              this.exp += this.percTemp;
            });
        }
      }
    },
  },
};
</script>
<style scoped>
.play {
  border-block-width: 0px;
  border-block-end: 10px;

  width: 329px;
  margin-bottom: 40px;
  margin-top: opx;
}
</style>
