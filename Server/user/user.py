import webapp2
import os
import jinja2

from google.appengine.ext import db

template_dir = os.path.join(os.path.dirname(__file__), 'templates')
jinja_env = jinja2.Environment(loader = jinja2.FileSystemLoader('templates'),
							   autoescape = True)

class User(db.Model):
	username = db.StringProperty(required = True)
	password = db.StringProperty(required = True)
	name = db.StringProperty(required = True)
	email = db.StringProperty(required = True);
	online = db.BooleanProperty()
							   
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
		users = db.Query(User)
		if text:
			users.filter('username =', text)
		self.render("front.html", text=text, users=users)

	def get(self):
		username = self.request.get("username")
		self.render_front(text=username)
	
	def post(self):
		username = self.request.get("username")
		password = self.request.get("password")
		name = self.request.get("name")
		email = self.request.get("email")
		
		if username and password and name and email:
			a = User(username=username, password=password, name=name, email=email) 
			a.put()
			self.redirect("/")
		else:
			self.render_front(text="POST")
		
app = webapp2.WSGIApplication([('/', MainPage)], debug=True)