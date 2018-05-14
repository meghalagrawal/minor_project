from django.shortcuts import render

# Create your views here.
from django.views.decorators.csrf import csrf_exempt
from users.models import UserData
from classes.models import ClassData
from assignments.models import AssignmentData, AssignmentSubmissionData
import requests
from django.http import JsonResponse
import keys
import jwt


@csrf_exempt
def assignments(request):
	if request.method=="GET":
		response_json={}
		try:
			access_token = request.GET.get("access_token")
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
				assignment_list.append(assignment_json)
			response_json["success"]=True
			response_json["message"]="Successful"
			response_json["assignment_list"]=assignment_list
		except Exception as e:
			response_json["success"]=False
			response_json["message"]="Something went wrong "+str(e)
			print(str(e))
		print(response_json)
		return JsonResponse(response_json)



@csrf_exempt
def submissions(request):
	if request.method=="GET":
		response_json={}
		try:
			access_token = request.GET.get(keys.KEY_REQUEST_ACCESS_TOKEN)
			assignment_id = request.GET.get("assignment_id")
			json1 = jwt.decode(str(access_token), keys.KEY_ACCESS_TOKEN_ENCRYPTION, algorithms=['HS256'])
			email = str(json1['access_token'])
			user_instance = UserData.objects.get(email=email)
			assignment_instance = AssignmentData.objects.get(id=assignment_id)
			submission_list_instance = AssignmentSubmissionData.objects.filter(assignment_instance=assignment_instance)
			submission_list=[]
			for submission in submission_list_instance:
				submission_json={}
				submission_json["submission_id"]=submission.id
				submission_json["time_taken"]=submission.time_taken
				submission_json["response"]=submission.response
				submission_json["created"]=str(submission.created)

				submission_list.append(submission_json)
			response_json["success"]=True
			response_json["message"]="Successful"
			response_json["submission_list"]=submission_list
		except Exception as e:
			response_json["success"]=False
			response_json["message"]="Something went wrong "+str(e)
			print(str(e))
		print(response_json)
		return JsonResponse(response_json)

@csrf_exempt
def compiler(request):
	if request.method=="POST":
		stdin = request.POST.get("stdin")
		language = request.POST.get("language")
		code = request.POST.get("code")
		payload = {'stdin':stdin,"language":language,"code":code}
		url = "http://139.59.71.124:9099/compile"
		try:
			response = requests.post(url, data=payload)
			print(response.content)
		except Exception as e:
			print(str(e))
		import json
		return JsonResponse(json.loads(response.content))

@csrf_exempt
def submit_assignment(request):
	response_json = {}
	if request.method=="POST":
		stdin = request.POST.get("stdin")
		language = request.POST.get("language")
		code = request.POST.get("code")
		access_token = request.POST.get(keys.KEY_REQUEST_ACCESS_TOKEN)
		assignment_id = request.POST.get("assignment_id")
		json1 = jwt.decode(str(access_token), keys.KEY_ACCESS_TOKEN_ENCRYPTION, algorithms=['HS256'])
		email = str(json1['access_token'])
		user_instance = UserData.objects.get(email=email)
		assignment_instance = AssignmentData.objects.get(id=assignment_id)


		payload = {'stdin':stdin,"language":language,"code":code}
		url = "http://139.59.71.124:9099/compile"
		try:
			response = requests.post(url, data=payload)
			response = json.loads(response.content)
			AssignmentSubmissionData.objects.create(user_instance = user_instance,assignment_instance = assignment_instance, submitted_code = code, time_taken = response.time,response = response)
			print(response.content)
			response_json["success"]=True
			response_json["message"]="Successfully saved!"

		except Exception as e:
			print(str(e))
			response_json["success"]=False
			response_json["message"]="Something went wrong! "+str(e) 
		
		return JsonResponse(response_json)















