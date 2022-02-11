var TheView = Backbone.View.extend({

	events: {
		"click #locate": "locate",
	},

	render: function() {

		var view = this;
		var jqxhr = jQuery.getJSON("api/info");
		jqxhr.done(function(data) {
			console.log("Got data " + JSON.stringify(data));

	        document.title = data.name;

			var html = [];
			html.push('<div class="center-block" style="width: 350px; padding-top: 18px">');
			
            html.push('<div class="row">');		
			html.push('<div class="col-md-12 highlight">');
			html.push(serviceDataTemplate(data));
			html.push('</div>');
            html.push('</div>');
			
			html.push('</div>');

			var allHtml = html.join("");

			$(view.el).html(allHtml);

		});

		return this;
	},
});

var serviceDataTemplate = _.template(
'<div class="col-md-12" style="text-align: center; height: 100px"> \
    <span class="glyphicon glyphicon-cloud" style="color: <%= color %>; font-size: 128px; margin: 0px"></span> \
</div> \
<div class="col-md-12" style="text-align: center;"> \
    <p style="font-size: 20px; margin: 5px">Name: <%= name %></p> \
    <p style="font-size: 20px; margin: 5px">IP Address: <%= containerAddress %></p> \
</div>');

window.Router = Backbone.Router.extend({
    routes: {
        "": "run",
    },

	initialize: function () {
	},

	run: function() {
		var view = new TheView();
		var content = $('#content');
		$(content).html(view.render().el);
	},
});

app = new Router();
Backbone.history.start();


