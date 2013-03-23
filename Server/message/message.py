import webapp2
import os
import jinja2

from google.appengine.ext import db

template_dir = os.path.join(os.path.dirname(__file__), 'templates')
jinja_env = jinja2.Environment(loader = jinja2.FileSystemLoader('templates'),
							   autoescape = True)

class Message(db.Model):
	#sender = db.StringProperty(required = True)
	sender = db.StringProperty(required = True)
	recipient = db.StringProperty(required = True)
	content = db.TextProperty(required = True)
	#type = db.StringProperty(required = True)
	type = db.StringProperty(required = True)
	created = db.DateTimeProperty(auto_now_add = True)	
							   
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
		#whereClause = ""
		#if text:
		#	whereClause = " WHERE recipient = " + text
		#messages = db.GqlQuery("SELECT * FROM Message "
		#					   "ORDER BY created DESC")
		
		
		messages = db.Query(Message)
		messages.order('-created')
		if text:
			messages.filter('recipient =', text)
			messages = messages.fetch(limit=10)
	
		self.render("front.html", text=text, messages=messages)

	def get(self):
		recipient = self.request.get("recipient")
		self.render_front(text=recipient)
	
	def post(self):
		sender = self.request.get("sender")
		recipient = self.request.get("recipient")
		content = self.request.get("content")
		type = self.request.get("type")
		
		if sender and recipient and content and type:
			#self.write("Thanks!")
			a = Message(sender=sender, recipient=recipient, content=content, type=type)
			a.put()
			self.redirect("/")
		else:
			self.render_front(text="POST")
			

		
app = webapp2.WSGIApplication([('/', MainPage)], debug=True)