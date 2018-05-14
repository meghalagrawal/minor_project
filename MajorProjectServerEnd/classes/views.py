from django.shortcuts import render
import keys
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
import jwt
from users.models import UserData
from classes.models import UserClassData, ClassData

# Create your views here.
@csrf_exempt
def classes(request):
	if request.method=="GET":
		response_json={}
		try:
			access_token = request.GET.get(keys.KEY_REQUEST_ACCESS_TOKEN)
			json1 = jwt.decode(str(access_token), keys.KEY_ACCESS_TOKEN_ENCRYPTION, algorithms=['HS256'])
			email = str(json1['access_token'])
			user_instance = UserData.objects.get(email=email)
			user_class_list = UserClassData.objects.filter(user_instance=user_instance)
			class_list=[]
			for user_class in user_class_list:
				class_json={}
				class_json["class_id"]=user_class.class_instance.id
				class_json["title"]=user_class.class_instance.title
				class_json["description"]=user_class.class_instance.description
				class_list.append(class_json)
			response_json["success"]=True
			response_json["message"]="Successful"
			response_json["class_list"]=class_list
		except Exception as e:
			response_json["success"]=False
			response_json["message"]="Something went wrong "+str(e)
			print(str(e))
	elif request.method=="POST":
		response_json={}
		try:
			access_token = request.POST.get(keys.KEY_REQUEST_ACCESS_TOKEN)
			class_code = request.POST.get("class_code")
			json1 = jwt.decode(str(access_token), keys.KEY_ACCESS_TOKEN_ENCRYPTION, algorithms=['HS256'])
			email = str(json1['access_token'])
			user_instance = UserData.objects.get(email=email)
			try:
				class_instance = ClassData.objects.get(class_code = class_code)
				UserClassData.objects.create(user_instance=user_instance,class_instance=class_instance)
				response_json["success"]=True
				response_json["message"]="Successful"
			except Exception as e:
				print(str(e))
				response_json["success"]=False
				response_json["message"]="Invalid Class Code! Please Re-Check the class code and try again "
		except Exception as e:
			response_json["success"]=False
			response_json["message"]="Something went wrong "+str(e)
			print(str(e))
	print(response_json)
	return JsonResponse(response_json)