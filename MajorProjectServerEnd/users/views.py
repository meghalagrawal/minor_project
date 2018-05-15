from django.shortcuts import render
from keys import KEY_RESPONSE_SUCCESS,KEY_RESPONSE_MESSAGE,KEY_JWT_ACCESS_TOKEN,KEY_ACCESS_TOKEN_ENCRYPTION,KEY_JWT_ACCESS_TOKEN
# Create your views here.
import keys
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from users.models import UserData
import jwt


@csrf_exempt
def login(request):
	response = {}
	if request.method == 'POST':
		try:
			email = request.POST.get("email")
			password = request.POST.get("password")
			user_instance = UserData.objects.filter(email=email, password=password)
			if user_instance.exists():
				response[keys.KEY_RESPONSE_SUCCESS] = True
				response[keys.KEY_RESPONSE_MESSAGE] = "Successful"
				access_token = jwt.encode({keys.KEY_JWT_ACCESS_TOKEN: email}, keys.KEY_ACCESS_TOKEN_ENCRYPTION,algorithm='HS256')
				response["access_token"] = access_token
			else:
				response[keys.KEY_RESPONSE_SUCCESS] = False
				response[keys.KEY_RESPONSE_MESSAGE] = "Email and password does not match"
		except Exception as e:
			response[keys.KEY_RESPONSE_SUCCESS] = False
			response[keys.KEY_RESPONSE_MESSAGE] = "Invalid Credential. Please try again."
			print(str(e))
		return JsonResponse(response)
	else:
		response[keys.KEY_RESPONSE_SUCCESS] = False
		response[keys.KEY_RESPONSE_MESSAGE] = "Illegal Request!"
		return JsonResponse(response)

@csrf_exempt
def signup(request):
	response = {}
	if request.method == 'POST':
		try:
			name = request.POST.get("name")
			email = request.POST.get("email")
			password = request.POST.get("password")
			user_instance = UserData.objects.filter(email=email)
			if user_instance.exists():
				response[keys.KEY_RESPONSE_SUCCESS] = False
				response[keys.KEY_RESPONSE_MESSAGE] = "This email is already associated with another user!"

				
			else:
				UserData.objects.create(email=email,password=password,name = name)
				response[keys.KEY_RESPONSE_SUCCESS] = True
				response[keys.KEY_RESPONSE_MESSAGE] = "Successfully Signed up!"
				access_token = jwt.encode({keys.KEY_JWT_ACCESS_TOKEN: email}, keys.KEY_ACCESS_TOKEN_ENCRYPTION,algorithm='HS256')
				response["access_token"] = access_token

		except Exception as e:
			response[keys.KEY_RESPONSE_SUCCESS] = False
			response[keys.KEY_RESPONSE_MESSAGE] = "Something went wrong! "+str(e)
			print(str(e))
		return JsonResponse(response)
	else:
		response[keys.KEY_RESPONSE_SUCCESS] = False
		response[keys.KEY_RESPONSE_MESSAGE] = "Illegal Request!"
		return JsonResponse(response)