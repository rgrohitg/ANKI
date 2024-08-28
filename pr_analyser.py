from flask import Blueprint, request, jsonify
from pydantic import ValidationError
from app.models import QueryRequest, QueryResponse, UpdateKnowledgeBaseRequest
from app.classifier import classify_query
from app.structured import route_structured_query
from app.unstructured import route_unstructured_query
from app.knowledge_base import update_knowledge_base

api_blueprint = Blueprint('api', __name__)

@api_blueprint.route('/classify-query', methods=['POST'])
def classify_and_route_query():
    try:
        req_data = request.get_json()
        query_request = QueryRequest(**req_data)

        category = classify_query(query_request.query)
        if category == "structured":
            response = route_structured_query(query_request.query)
        elif category == "unstructured":
            response = route_unstructured_query(query_request.query)
        else:
            return jsonify({"error": "Unknown query category"}), 400

        if "error" in response:
            return jsonify({"error": response["error"]}), 500

        query_response = QueryResponse(category=category, response=response)
        return jsonify(query_response.dict())
    except ValidationError as e:
        return jsonify({"error": e.errors()}), 400
    except Exception as e:
        return jsonify({"error": str(e)}), 500

@api_blueprint.route('/update-knowledge-base', methods=['POST'])
def update_knowledge_base_endpoint():
    try:
        req_data = request.get_json()
        update_request = UpdateKnowledgeBaseRequest(**req_data)
        update_knowledge_base(update_request.keywords)
        return jsonify({"message": "Knowledge base updated successfully"})
    except ValidationError as e:
        return jsonify({"error": e.errors()}), 400
    except Exception as e:
        return jsonify({"error": str(e)}), 500
