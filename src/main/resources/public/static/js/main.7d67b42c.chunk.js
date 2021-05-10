(this.webpackJsonpfrontend=this.webpackJsonpfrontend||[]).push([[0],{25:function(e,t,c){},26:function(e,t,c){},28:function(e,t,c){},35:function(e,t,c){},36:function(e,t,c){},37:function(e,t,c){},38:function(e,t,c){},39:function(e,t,c){},40:function(e,t,c){},41:function(e,t,c){},42:function(e,t,c){"use strict";c.r(t);var a=c(1),n=c.n(a),s=c(18),r=c.n(s),i=(c(25),c(4)),j=c(2),l=(c(26),c(8)),h=c.n(l),o=c(10),m=c(11),d=(c(28),c(0)),u=function(e){var t=e.teams;return Object(d.jsx)("ul",{className:"Teams",children:t.map((function(e){return Object(d.jsx)("li",{className:"Team",children:Object(d.jsx)(i.b,{to:"/teams/".concat(e.name),children:e.name})},e.id)}))})},b=(c(35),function(){var e=Object(a.useState)([]),t=Object(m.a)(e,2),c=t[0],n=t[1];return Object(a.useEffect)((function(){(function(){var e=Object(o.a)(h.a.mark((function e(){var t,c;return h.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("http://localhost:8081/team");case 2:return t=e.sent,e.next=5,t.json();case 5:c=e.sent,n(c);case 7:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}})()()}),[]),!c||c.length<1?Object(d.jsx)("div",{className:"Loader",children:"Loading..."}):Object(d.jsxs)("div",{className:"HomePage",children:[Object(d.jsx)("h1",{children:"IPL Dashboard"}),Object(d.jsx)(u,{teams:c})]})}),O=(c(36),function(e){var t=e.teamName,c=e.match;if(!c)return null;var a=t===c.team1?c.team2:c.team1,n="/teams/".concat(a);return Object(d.jsxs)("div",{className:t===c.matchWinner?"MatchDetailCard Win":"MatchDetailCard Loss",children:[Object(d.jsxs)("div",{className:"BasicDetail",children:[Object(d.jsx)("span",{className:"Vs",children:"vs"}),Object(d.jsx)("h1",{children:Object(d.jsx)(i.b,{to:n,children:a})}),Object(d.jsx)("h2",{className:"MatchDate",children:c.date}),Object(d.jsxs)("h3",{className:"MatchVenu",children:["at ",c.venue]}),Object(d.jsxs)("h3",{className:"MatchResult",children:[c.matchWinner," won by ",c.resultMargin," ",c.result]})]}),Object(d.jsxs)("div",{className:"AdditionalDetail",children:[Object(d.jsx)("h3",{children:"First Inning"}),Object(d.jsx)("p",{children:c.team1}),Object(d.jsx)("h3",{children:"Second Inning"}),Object(d.jsx)("p",{children:c.team2}),Object(d.jsx)("h3",{children:"Man of the match"}),Object(d.jsx)("p",{children:c.playerOfMatch}),Object(d.jsx)("h3",{children:"Umpires"}),Object(d.jsxs)("p",{children:[Object(d.jsx)("span",{children:c.umpire1}),", ",Object(d.jsx)("span",{children:c.umpire2})]})]})]})}),x=(c(37),function(e){for(var t=e.teamName,c=[],a="2008";a<="2020";a++)c.push(a);"/teams/".concat(t,"/");return Object(d.jsx)("ol",{className:"YearSelector",children:c.map((function(e){return Object(d.jsx)("li",{children:Object(d.jsx)(i.b,{to:"/teams/".concat(t,"/matches/").concat(e),children:e})},e)}))})}),f=(c(38),function(){var e=Object(a.useState)([]),t=Object(m.a)(e,2),c=t[0],n=t[1],s=Object(j.f)(),r=s.teamName,i=s.year;return Object(a.useEffect)((function(){(function(){var e=Object(o.a)(h.a.mark((function e(){var t,c;return h.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("http://localhost:8081/team/".concat(r,"/matches?year=").concat(i));case 2:return t=e.sent,e.next=5,t.json();case 5:c=e.sent,n(c);case 7:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}})()()}),[i,r]),Object(d.jsxs)("div",{className:"MatchPage",children:[Object(d.jsxs)("div",{className:"YearSelector-Section",children:[Object(d.jsx)("h3",{children:"Select Year"}),Object(d.jsx)(x,{teamName:r})]}),Object(d.jsxs)("div",{className:"Matches",children:[Object(d.jsxs)("h1",{className:"Matches-Heading",children:[r," matches in ",i]}),c.map((function(e){return Object(d.jsx)(O,{teamName:r,match:e},e.id)}))]})]})}),p=(c(39),function(e){var t=e.teamName,c=e.match;if(!c)return null;var a=t===c.team1?c.team2:c.team1,n="/teams/".concat(a);return Object(d.jsxs)("div",{className:t===c.matchWinner?"MatchSmallCard Win":"MatchSmallCard Loss",children:[Object(d.jsxs)("div",{children:[Object(d.jsx)("span",{children:"vs"}),Object(d.jsx)("h2",{children:Object(d.jsx)(i.b,{to:n,children:a})})]}),Object(d.jsxs)("p",{className:"MatchWinner",children:[c.matchWinner," won by ",c.resultMargin," ",c.result]})]})}),v=c(20),N=(c(40),function(e){var t=e.totalMatches,c=e.wins,a=e.losses,n=(100*c/t).toFixed(),s=(100*a/t).toFixed();return Object(d.jsxs)("div",{className:"WinVsLossPieChart",children:[Object(d.jsx)("p",{className:"WinLoss-Title",children:"Wins / Losses"}),Object(d.jsx)(v.PieChart,{data:[{title:s+"% Loss",value:a,color:"#d17a77"},{title:n+"% Win",value:c,color:"#77d18c"}]})]})}),M=(c(41),function(){var e=Object(a.useState)({matches:[]}),t=Object(m.a)(e,2),c=t[0],n=t[1],s=Object(j.f)().teamName;return Object(a.useEffect)((function(){(function(){var e=Object(o.a)(h.a.mark((function e(){var t,c;return h.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("http://localhost:8081/team/".concat(s));case 2:return t=e.sent,e.next=5,t.json();case 5:c=e.sent,n(c);case 7:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}})()()}),[s]),c&&c.name?Object(d.jsxs)("div",{className:"TeamPage",children:[Object(d.jsx)("div",{className:"TeamName-Section",children:Object(d.jsx)("h1",{className:"TeamName",children:c.name})}),Object(d.jsx)("div",{className:"Win-Loss-Section",children:Object(d.jsx)(N,{totalMatches:c.totalMatches,wins:c.totalWins,losses:c.totalMatches-c.totalWins})}),Object(d.jsxs)("div",{className:"MatchDetailCard-Section",children:[Object(d.jsx)("h3",{children:"Latest Matches"}),Object(d.jsx)(O,{teamName:c.name,match:c.matches[0]})]}),c.matches.slice(1).map((function(e){return Object(d.jsx)(p,{teamName:c.name,match:e},e.id)})),Object(d.jsx)("div",{className:"More-Section",children:Object(d.jsx)(i.b,{to:"/teams/".concat(c.name,"/matches/").concat("2020"),children:"More \u2192"})})]}):Object(d.jsx)("h1",{children:"Sorry, team not found."})});var S=function(){return Object(d.jsx)("div",{className:"App",children:Object(d.jsx)(i.a,{children:Object(d.jsxs)(j.c,{children:[Object(d.jsx)(j.a,{path:"/teams/:teamName/matches/:year",children:Object(d.jsx)(f,{})}),Object(d.jsx)(j.a,{path:"/teams/:teamName",children:Object(d.jsx)(M,{})}),Object(d.jsx)(j.a,{path:"/",children:Object(d.jsx)(b,{})})]})})})},g=function(e){e&&e instanceof Function&&c.e(3).then(c.bind(null,43)).then((function(t){var c=t.getCLS,a=t.getFID,n=t.getFCP,s=t.getLCP,r=t.getTTFB;c(e),a(e),n(e),s(e),r(e)}))};r.a.render(Object(d.jsx)(n.a.StrictMode,{children:Object(d.jsx)(S,{})}),document.getElementById("root")),g()}},[[42,1,2]]]);
//# sourceMappingURL=main.7d67b42c.chunk.js.map