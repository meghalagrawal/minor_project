from django.shortcuts import render

# Create your views here.
from django.views.decorators.csrf import csrf_exempt
from users.models import UserData
from classes.models import ClassData
from assignments.models import AssignmentData

@csrf_exempt
def assignments(request):
	if request.method=="GET":
		response_json={}
		try:
			access_token = request.GET.get(keys.KEY_REQUEST_ACCESS_TOKEN)
			class_id = request.GET.get("class_id")
			json1 = jwt.decode(str(access_token), keys.KEY_ACCESS_TOKEN_ENCRYPTION, algorithms=['HS256'])
			email = str(json1['access_token'])
			user_instance = UserData.objects.get(email=email)
			class_instance = ClassData.objects.get(id=class_id)
			assignment_list_instance = AssignmentData.objects.filter(class_instance=class_instance)
			assignment_list=[]
			for assignment in assignment_list_instance:
				assignment_json={}
				assignment_json["assignment_id"]=assignment.id
				assignment_json["title"]=assignment.title
				assignment_json["description"]=assignment.description
				assignment_json["due_date"]=assignment.due_date
				assignment_json["time_limit"]=assignment.time_limit

				assignment_json.append(assignment_json)
			response_json["success"]=True
			response_json["message"]="Successful"
			response_json["class_list"]=assignment_list
		except Exception as e:
			response_json["success"]=False
			response_json["message"]="Something went wrong "+str(e)
			print(str(e))