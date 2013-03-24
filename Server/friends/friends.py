import webapp2
import os
import jinja2

from google.appengine.ext import db

template_dir = os.path.join(os.path.dirname(__file__), 'templates')
jinja_env = jinja2.Environment(loader = jinja2.FileSystemLoader('templates'),
							   autoescape = True)

class Friends(db.Model):
	user = db.StringProperty(required = True)
	friend = db.StringProperty(required = True)
							   
class Handler(webapp2.RequestHandler):
	def write(self, *a, **kw):
		self.response.out.write(*a, **kw)
		
	def render_str(self, template, **params):
		t = jinja_env.get_template(template)
		return t.render(params)

	def render(self, template, **kw):
		self.write(self.render_str(template, **kw))
						
class MainPage(Handler):
	def render_front(self, text=""):
		friends = db.Query(Friends)
		if text:
			friends.filter('user =', text)
			#friends.order('friend');
		self.render("front.html", text=text, friends=friends)

	def get(self):
		user = self.request.get("user")
		self.render_front(text=user)
	
	def post(self):
		user = self.request.get("user")
		friend = self.request.get("friend")
		
		if user and friend:
			a = Friends(user=user, friend=friend) 
			a.put()
			self.redirect("/")
		else:
			self.render_front(text="POST")
		
app = webapp2.WSGIApplication([('/', MainPage)], debug=True)