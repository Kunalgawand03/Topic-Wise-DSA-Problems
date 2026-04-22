// ── CONFIG ──────────────────────────────────────────
const GEMINI_API_KEY = "YOUR_GEMINI_API_KEY";  // 🔑 Put your key here
const SUBJECT = "DBMS";

// ── STATE ────────────────────────────────────────────
let submittedDoubts = [];   // stores all submitted doubts
let activeTags = [];        // stores selected tags
let lastSuggestion = "";    // stores AI enhanced text

// ── TAG TOGGLE ───────────────────────────────────────
function toggleTag(el) {
  el.classList.toggle("active");
  const tag = el.textContent;

  if (el.classList.contains("active")) {
    activeTags.push(tag);       // add tag if selected
  } else {
    activeTags = activeTags.filter(t => t !== tag);  // remove if deselected
  }
}

// ── CALL GEMINI AI ───────────────────────────────────
async function callGemini(prompt) {
  const url = `https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=${GEMINI_API_KEY}`;

  const response = await fetch(url, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      contents: [{ parts: [{ text: prompt }] }]
    })
  });

  const data = await response.json();
  return data.candidates[0].content.parts[0].text;
}

// ── SHOW AI RESULT BOX ───────────────────────────────
function showAIResult(text, showUseBtn = false) {
  document.getElementById("ai-result").style.display = "block";
  document.getElementById("ai-text").textContent = text;
  document.getElementById("use-btn").style.display = showUseBtn ? "inline-block" : "none";
  lastSuggestion = text;
}

// ── USE AI SUGGESTION ────────────────────────────────
function useSuggestion() {
  document.getElementById("doubt-text").value = lastSuggestion;
}

// ── AI ENHANCE ───────────────────────────────────────
async function enhanceDoubt() {
  const doubt = document.getElementById("doubt-text").value.trim();
  if (!doubt) { alert("Please type your doubt first."); return; }

  showAIResult("Thinking...", false);

  const prompt = `Rewrite this student doubt to make it clearer for the subject "${SUBJECT}". Give only the improved version, no explanation.

Doubt: "${doubt}"`;

  try {
    const result = await callGemini(prompt);
    showAIResult(result.trim(), true);  // show "Use This" button
  } catch (e) {
    showAIResult("Error: Could not reach AI. Check your API key.");
  }
}

// ── AUTO TAG ─────────────────────────────────────────
async function autoTag() {
  const doubt = document.getElementById("doubt-text").value.trim();
  if (!doubt) { alert("Please type your doubt first."); return; }

  showAIResult("Finding tags...", false);

  const prompt = `Given this student doubt for "${SUBJECT}", give 2 tags from: Conceptual, Numerical, Definition, Example.
Respond ONLY in JSON like: {"tags":["Tag1","Tag2"]}

Doubt: "${doubt}"`;

  try {
    const raw = await callGemini(prompt);
    const clean = raw.replace(/```json|```/gi, "").trim();
    const parsed = JSON.parse(clean);

    // Highlight matching tags in the UI
    document.querySelectorAll(".tag").forEach(el => {
      if (parsed.tags.includes(el.textContent)) {
        el.classList.add("active");
        if (!activeTags.includes(el.textContent)) {
          activeTags.push(el.textContent);
        }
      }
    });

    showAIResult("Tags applied: " + parsed.tags.join(", "));
  } catch (e) {
    showAIResult("Could not auto-tag. Try again.");
  }
}

// ── GRAMMAR CHECK ────────────────────────────────────
async function grammarCheck() {
  const doubt = document.getElementById("doubt-text").value.trim();
  if (!doubt) { alert("Please type your doubt first."); return; }

  showAIResult("Checking grammar...", false);

  const prompt = `Check grammar and spelling of this student doubt. List corrections as bullets. If no errors, say "No errors found."

Doubt: "${doubt}"`;

  try {
    const result = await callGemini(prompt);
    showAIResult(result.trim());
  } catch (e) {
    showAIResult("Error: Could not reach AI.");
  }
}

// ── SUBMIT DOUBT ─────────────────────────────────────
async function submitDoubt() {
  const name = document.getElementById("student-name").value.trim();
  const doubt = document.getElementById("doubt-text").value.trim();
  const errorMsg = document.getElementById("error-msg");

  // Simple validation
  if (!name) { errorMsg.textContent = "Please enter your name."; return; }
  if (doubt.length < 10) { errorMsg.textContent = "Doubt is too short (min 10 characters)."; return; }
  errorMsg.textContent = "";

  // Check if doubt is relevant using AI
  let relevance = "Relevant";
  try {
    const prompt = `Is this doubt related to "${SUBJECT}"? Reply with only one word: Relevant or Irrelevant.
Doubt: "${doubt}"`;
    const reply = await callGemini(prompt);
    if (reply.toLowerCase().includes("irrelevant")) relevance = "Irrelevant";
  } catch (e) {
    // If AI fails, just mark as Relevant and continue
  }

  // Save the doubt
  const newDoubt = {
    name: name,
    text: doubt,
    tags: [...activeTags],
    relevance: relevance,
    time: new Date().toLocaleTimeString()
  };

  submittedDoubts.unshift(newDoubt);          // add to top of list
  document.getElementById("count").textContent = submittedDoubts.length;

  // Update the history display
  renderHistory();

  // Clear the form
  document.getElementById("student-name").value = "";
  document.getElementById("doubt-text").value = "";
  document.getElementById("ai-result").style.display = "none";
  document.querySelectorAll(".tag").forEach(t => t.classList.remove("active"));
  activeTags = [];

  alert(relevance === "Relevant"
    ? "✅ Doubt submitted successfully!"
    : "⚠️ Submitted, but this may be off-topic for " + SUBJECT);
}

// ── RENDER HISTORY ────────────────────────────────────
function renderHistory() {
  const container = document.getElementById("history");

  if (submittedDoubts.length === 0) {
    container.innerHTML = '<p class="empty">No doubts yet.</p>';
    return;
  }

  container.innerHTML = submittedDoubts.map(d => `
    <div class="doubt-card">
      <p>${d.text}</p>
      <div class="meta">
        <span>👤 ${d.name}</span>
        <span>🕐 ${d.time}</span>
        <span class="badge ${d.relevance.toLowerCase()}">${d.relevance}</span>
        ${d.tags.map(t => `<span class="badge">#${t}</span>`).join("")}
      </div>
    </div>
  `).join("");
}